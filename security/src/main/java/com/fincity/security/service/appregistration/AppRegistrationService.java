package com.fincity.security.service.appregistration;

import java.util.List;

import org.jooq.types.ULong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fincity.nocode.reactor.util.FlatMapUtil;
import com.fincity.saas.commons.exeception.GenericException;
import com.fincity.saas.commons.security.util.SecurityContextUtil;
import com.fincity.saas.commons.util.LogUtil;
import com.fincity.security.dao.appregistration.AppRegistrationDAO;
import com.fincity.security.dto.AppRegistrationAccess;
import com.fincity.security.dto.AppRegistrationFile;
import com.fincity.security.dto.AppRegistrationIntegration;
import com.fincity.security.dto.AppRegistrationIntegrationScope;
import com.fincity.security.dto.AppRegistrationPackage;
import com.fincity.security.dto.AppRegistrationRole;
import com.fincity.security.enums.ClientLevelType;
import com.fincity.security.model.IntegrationScope;
import com.fincity.security.service.AppService;
import com.fincity.security.service.ClientService;
import com.fincity.security.service.PackageService;
import com.fincity.security.service.RoleService;
import com.fincity.security.service.SecurityMessageResourceService;
import com.fincity.security.service.appintegration.IntegrationScopeService;
import com.fincity.security.service.appintegration.IntegrationService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

@Service
public class AppRegistrationService {

    public static final String DEFAULT_BUSINESS_TYPE = "COMMON";

    private final AppService appService;
    private final RoleService roleService;
    private final PackageService packageService;
    private final ClientService clientService;
    private final IntegrationService integrationService;
    private final IntegrationScopeService integrationScopeService;

    private final SecurityMessageResourceService messageService;

    private final AppRegistrationDAO dao;

    public AppRegistrationService(AppService appService, RoleService roleService,
            PackageService packageService, ClientService clientService,
            IntegrationService integrationService,
            IntegrationScopeService integrationScopeService,
            AppRegistrationDAO appRegistrationDAO,
            SecurityMessageResourceService messageService) {
        this.appService = appService;
        this.roleService = roleService;
        this.packageService = packageService;
        this.clientService = clientService;
        this.integrationService = integrationService;
        this.integrationScopeService = integrationScopeService;
        this.dao = appRegistrationDAO;
        this.messageService = messageService;
    }

    public Mono<Boolean> deleteEverything(ULong id, boolean forceDelete) {

        return FlatMapUtil.flatMapMono(

                SecurityContextUtil::getUsersContextAuthentication,

                ca -> this.appService.read(id),

                (ca, app) -> {
                    if (ca.isSystemClient() || app.getClientId().equals(id)
                            || app.getClientId().toBigInteger()
                                    .equals(ca.getLoggedInFromClientId())) {
                        if (forceDelete) {
                            return Mono.just(true);
                        }
                        return this.appService.isNoneUsingTheAppOtherThan(id,
                                ca.getUser().getClientId());
                    }

                    return Mono.just(false);
                },

                (ca, app, hasAccess) -> hasAccess.booleanValue() ? Mono.just(true) : Mono.empty(),

                (ca, app, hasAccess, delete) -> this.dao.deleteEverythingRelated(id))
                .contextWrite(Context.of(LogUtil.METHOD_NAME,
                        "AppRegistrationService.deleteEverything"));
    }

    public Mono<AppRegistrationAccess> createAccess(String appCode, AppRegistrationAccess access) {

        return FlatMapUtil.flatMapMono(

                SecurityContextUtil::getUsersContextAuthentication,

                ca -> this.appService.getAppByCode(appCode),

                (ca, app) -> this.appService.hasWriteAccess(appCode, ca.getClientCode())
                        .filter(e -> e),

                (ca, app, hasWriteAccess) -> this.appService.getAppById(access.getAllowAppId()),

                (ca, app, hasWriteAccess, allowedApp) -> this.appService
                        .hasWriteAccess(allowedApp.getAppCode(), ca.getClientCode())
                        .filter(e -> e),

                (ca, app, hasWriteAccess, allowedApp, hasWriteAccessToAllowedApp) -> {

                    if (access.getClientId() == null) {
                        access.setClientId(ULong.valueOf(ca.getUser().getClientId()));
                        return Mono.just(true);
                    }

                    if (ca.isSystemClient()) {
                        Mono.just(true);
                    }

                    return this.clientService
                            .isBeingManagedBy(ULong.valueOf(ca.getUser().getClientId()),
                                    access.getClientId())
                            .filter(e -> e);
                },

                (ca, app, hasWriteAccess, allowedApp, hasWriteAccessToAllowedApp,
                        isBeingManaged) -> this.dao
                                .createAccess(app, access)
                                .flatMap(this::fill)

        )
                .switchIfEmpty(this.messageService.throwMessage(
                        msg -> new GenericException(HttpStatus.FORBIDDEN, msg),
                        SecurityMessageResourceService.FORBIDDEN_APP_REG_OBJECTS, "App Access"))
                .contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.createAccess"));
    }

    public Mono<AppRegistrationAccess> getAccessById(ULong id) {
        return FlatMapUtil.flatMapMono(

                SecurityContextUtil::getUsersContextAuthentication,

                ca -> this.dao.getAccessById(id),

                (ca, access) -> ca.isSystemClient() ? Mono.just(true)
                        : this.appService
                                .hasWriteAccess(access.getAppId(),
                                        ULong.valueOf(ca.getUser()
                                                .getClientId()))
                                .filter(e -> e),

                (ca, access, hasAccess) -> ca.isSystemClient() ? Mono.just(true)
                        : this.appService
                                .hasWriteAccess(access.getAllowAppId(),
                                        ULong.valueOf(ca.getUser()
                                                .getClientId()))
                                .filter(e -> e),

                (ca, access, hasAccess, hasAccessToAllowedApp) -> ca.isSystemClient() ? Mono.just(true)
                        : this.clientService
                                .isBeingManagedBy(
                                        ULong.valueOf(ca.getUser()
                                                .getClientId()),
                                        access.getClientId())
                                .filter(e -> e),

                (ca, access, hasAccess, hasAccessToAllowedApp, isBeingManaged) -> this.fill(access)

        ).contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.getAccessById"));
    }

    public Mono<Boolean> deleteAccess(ULong id) {
        return FlatMapUtil.flatMapMono(

                () -> this.getAccessById(id),

                acc -> this.dao.deleteAccess(id)

        ).contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.deleteAccess"));
    }

    public Mono<Page<AppRegistrationAccess>> getAccess(String appCode, String clientCode,
            ULong clientId,
            String clientType, ClientLevelType level, String businessType, Pageable pageable) {

        if (clientCode != null && clientId != null) {
            return this.messageService.throwMessage(
                    msg -> new GenericException(HttpStatus.BAD_REQUEST, msg),
                    SecurityMessageResourceService.CLIENT_CODE_OR_ID_ONLY_ONE);
        }

        return FlatMapUtil.flatMapMono(

                SecurityContextUtil::getUsersContextAuthentication,

                ca -> this.appService.getAppByCode(appCode),

                (ca, app) -> ca.isSystemClient() ? Mono.just(true)
                        : this.appService.hasWriteAccess(appCode, ca.getClientCode())
                                .filter(e -> e),

                (ca, app, hasWriteAccess) -> {

                    if (!ca.isSystemClient() || (clientId == null && clientCode == null)) {
                        return Mono.just(ULong.valueOf(ca.getUser().getClientId()));
                    }

                    return (clientCode != null
                            ? this.clientService.getClientBy(clientCode).map(e -> e.getId())
                            : Mono.just(clientId))
                            .flatMap(id -> this.clientService
                                    .isBeingManagedBy(
                                            ULong.valueOf(ca.getUser()
                                                    .getClientId()),
                                            id)
                                    .filter(e -> e)
                                    .map(e -> id));
                },

                (ca, app, hasWriteAccess, newClientId) -> {

                    Mono<Page<AppRegistrationAccess>> page = this.dao.getAccess(app.getId(),
                            newClientId, clientType,
                            level, businessType, pageable);

                    return page.flatMap(e -> Flux.fromIterable(e.getContent())
                            .flatMap(this::fill).collectList().map(x -> e));
                }

        ).contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.getAccess"));
    }

    private Mono<AppRegistrationAccess> fill(AppRegistrationAccess access) {

        return FlatMapUtil.flatMapMono(
                () -> this.appService.getAppById(access.getAppId()),

                app -> this.appService.getAppById(access.getAllowAppId()),

                (app, allowedApp) -> this.clientService.getClientInfoById(access.getClientId()),

                (app, allowedApp, client) -> Mono
                        .just((AppRegistrationAccess) access.setAllowApp(allowedApp).setApp(app)
                                .setClient(client))

        ).contextWrite(
                Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.fill(AppRegistrationAccess)"));
    }

    public Mono<AppRegistrationFile> createFile(String appCode, AppRegistrationFile file) {

        return FlatMapUtil.flatMapMono(

                SecurityContextUtil::getUsersContextAuthentication,

                ca -> this.appService.getAppByCode(appCode),

                (ca, app) -> this.appService.hasWriteAccess(appCode, ca.getClientCode())
                        .filter(e -> e),

                (ca, app, hasWriteAccess) -> {

                    if (file.getClientId() == null) {
                        file.setClientId(ULong.valueOf(ca.getUser().getClientId()));
                        return Mono.just(true);
                    }

                    if (ca.isSystemClient()) {
                        Mono.just(true);
                    }

                    return this.clientService
                            .isBeingManagedBy(ULong.valueOf(ca.getUser().getClientId()),
                                    file.getClientId())
                            .filter(e -> e);
                },

                (ca, app, hasWriteAccess, isBeingManaged) -> this.dao.createFile(app, file)
                        .flatMap(this::fill)

        )
                .switchIfEmpty(this.messageService.throwMessage(
                        msg -> new GenericException(HttpStatus.FORBIDDEN, msg),
                        SecurityMessageResourceService.FORBIDDEN_APP_REG_OBJECTS,
                        "File Access"))
                .contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.createFile"));
    }

    public Mono<AppRegistrationFile> getFileById(ULong id) {
        return FlatMapUtil.flatMapMono(

                SecurityContextUtil::getUsersContextAuthentication,

                ca -> this.dao.getFileById(id),

                (ca, file) -> ca.isSystemClient() ? Mono.just(true)
                        : this.appService
                                .hasWriteAccess(file.getAppId(),
                                        ULong.valueOf(ca.getUser()
                                                .getClientId()))
                                .filter(e -> e),

                (ca, file, hasAccess) -> ca.isSystemClient() ? Mono.just(true)
                        : this.clientService
                                .isBeingManagedBy(
                                        ULong.valueOf(ca.getUser()
                                                .getClientId()),
                                        file.getClientId())
                                .filter(e -> e),

                (ca, file, hasAccess, isBeingManaged) -> this.fill(file)

        ).contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.getFileById"));
    }

    public Mono<Boolean> deleteFile(ULong id) {
        return FlatMapUtil.flatMapMono(

                () -> this.getFileById(id),

                file -> this.dao.deleteFile(id)

        ).contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.deleteFile"));
    }

    public Mono<Page<AppRegistrationFile>> getFile(String appCode, String clientCode, ULong clientId,
            String clientType,
            ClientLevelType level, String businessType, Pageable pageable) {

        if (clientCode != null && clientId != null) {
            return this.messageService.throwMessage(
                    msg -> new GenericException(HttpStatus.BAD_REQUEST, msg),
                    SecurityMessageResourceService.CLIENT_CODE_OR_ID_ONLY_ONE);
        }

        return FlatMapUtil.flatMapMono(

                SecurityContextUtil::getUsersContextAuthentication,

                ca -> this.appService.getAppByCode(appCode),

                (ca, app) -> ca.isSystemClient() ? Mono.just(true)
                        : this.appService.hasWriteAccess(appCode, ca.getClientCode())
                                .filter(e -> e),

                (ca, app, hasWriteAccess) -> {

                    if (ca.isSystemClient() || (clientId == null && clientCode == null)) {
                        return Mono.just(ULong.valueOf(ca.getUser().getClientId()));
                    }

                    return (clientCode != null
                            ? this.clientService.getClientBy(clientCode).map(e -> e.getId())
                            : Mono.just(clientId))
                            .flatMap(id -> this.clientService
                                    .isBeingManagedBy(
                                            ULong.valueOf(ca.getUser()
                                                    .getClientId()),
                                            id)
                                    .filter(e -> e)
                                    .map(e -> id));
                },

                (ca, app, hasWriteAccess, newClientId) -> {

                    Mono<Page<AppRegistrationFile>> page = this.dao.getFile(app.getId(),
                            newClientId, clientType, level,
                            businessType, pageable);

                    return page.flatMap(e -> Flux.fromIterable(e.getContent())
                            .flatMap(this::fill).collectList().map(x -> e));
                }

        ).contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.getFile"));
    }

    private Mono<AppRegistrationFile> fill(AppRegistrationFile file) {

        return FlatMapUtil.flatMapMono(
                () -> this.appService.getAppById(file.getAppId()),

                app -> this.clientService.getClientInfoById(file.getClientId()),

                (app, client) -> Mono
                        .just((AppRegistrationFile) file.setApp(app).setClient(client))

        ).contextWrite(
                Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.fill(AppRegistrationFile)"));
    }

    public Mono<AppRegistrationPackage> createPackage(String appCode, AppRegistrationPackage pack) {

        return FlatMapUtil.flatMapMono(

                SecurityContextUtil::getUsersContextAuthentication,

                ca -> this.appService.getAppByCode(appCode),

                (ca, app) -> this.appService.hasWriteAccess(appCode, ca.getClientCode())
                        .filter(e -> e),

                (ca, app, hasWriteAccess) -> {

                    if (pack.getClientId() == null) {
                        pack.setClientId(ULong.valueOf(ca.getUser().getClientId()));
                        return Mono.just(true);
                    }

                    if (ca.isSystemClient()) {
                        Mono.just(true);
                    }

                    return this.clientService
                            .isBeingManagedBy(ULong.valueOf(ca.getUser().getClientId()),
                                    pack.getClientId())
                            .filter(e -> e);
                },

                (ca, app, hasWriteAccess, isBeingManaged) -> this.clientService
                        .hasPackageAccess(ULong.valueOf(ca.getUser().getClientId()),
                                pack.getPackageId())
                        .filter(e -> e),

                (ca, app, hasWriteAccess, isBeingManaged, hasPackage) -> this.dao
                        .createPackage(app, pack)
                        .flatMap(this::fill)

        )
                .switchIfEmpty(this.messageService.throwMessage(
                        msg -> new GenericException(HttpStatus.FORBIDDEN, msg),
                        SecurityMessageResourceService.FORBIDDEN_APP_REG_OBJECTS,
                        "Package Access"))
                .contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.createPackage"));
    }

    public Mono<AppRegistrationPackage> getPackageById(ULong id) {
        return FlatMapUtil.flatMapMono(

                SecurityContextUtil::getUsersContextAuthentication,

                ca -> this.dao.getPackageById(id),

                (ca, pack) -> ca.isSystemClient() ? Mono.just(true)
                        : this.appService
                                .hasWriteAccess(pack.getAppId(),
                                        ULong.valueOf(ca.getUser()
                                                .getClientId()))
                                .filter(e -> e),

                (ca, pack, hasAccess) -> ca.isSystemClient() ? Mono.just(true)
                        : this.clientService
                                .isBeingManagedBy(
                                        ULong.valueOf(ca.getUser()
                                                .getClientId()),
                                        pack.getClientId())
                                .filter(e -> e),

                (ca, pack, hasAccess, isBeingManaged) -> this.fill(pack)

        ).contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.getPackageById"));
    }

    public Mono<Boolean> deletePackage(ULong id) {
        return FlatMapUtil.flatMapMono(

                () -> this.getPackageById(id),

                pack -> this.dao.deletePackage(id)

        ).contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.deletePackage"));
    }

    public Mono<Page<AppRegistrationPackage>> getPackage(String appCode, String packageName,
            String clientCode,
            ULong clientId, String clientType, ClientLevelType level, String businessType,
            Pageable pageable) {

        if (clientCode != null && clientId != null) {
            return this.messageService.throwMessage(
                    msg -> new GenericException(HttpStatus.BAD_REQUEST, msg),
                    SecurityMessageResourceService.CLIENT_CODE_OR_ID_ONLY_ONE);
        }

        return FlatMapUtil.flatMapMono(

                SecurityContextUtil::getUsersContextAuthentication,

                ca -> this.appService.getAppByCode(appCode),

                (ca, app) -> ca.isSystemClient() ? Mono.just(true)
                        : this.appService.hasWriteAccess(appCode, ca.getClientCode())
                                .filter(e -> e),

                (ca, app, hasWriteAccess) -> {

                    if (ca.isSystemClient() || (clientId == null && clientCode == null)) {
                        return Mono.just(ULong.valueOf(ca.getUser().getClientId()));
                    }

                    return (clientCode != null
                            ? this.clientService.getClientBy(clientCode).map(e -> e.getId())
                            : Mono.just(clientId))
                            .flatMap(id -> this.clientService
                                    .isBeingManagedBy(
                                            ULong.valueOf(ca.getUser()
                                                    .getClientId()),
                                            id)
                                    .filter(e -> e)
                                    .map(e -> id));
                },

                (ca, app, hasWriteAccess, newClientId) -> {

                    Mono<Page<AppRegistrationPackage>> page = this.dao.getPackage(app.getId(),
                            packageName, newClientId,
                            clientType, level, businessType, pageable);

                    return page.flatMap(e -> Flux.fromIterable(e.getContent())
                            .flatMap(this::fill).collectList().map(x -> e));
                }

        ).contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.getPackage"));
    }

    private Mono<AppRegistrationPackage> fill(AppRegistrationPackage pack) {

        return FlatMapUtil.flatMapMono(
                () -> this.appService.getAppById(pack.getAppId()),

                app -> this.clientService.getClientInfoById(pack.getClientId()),

                (app, client) -> this.packageService.read(pack.getPackageId()),

                (app, client, packageInfo) -> Mono
                        .just((AppRegistrationPackage) pack.setPackageDetails(packageInfo)
                                .setApp(app)
                                .setClient(client))

        ).contextWrite(
                Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.fill(AppRegistrationPackage)"));
    }

    public Mono<AppRegistrationRole> createRole(String appCode, AppRegistrationRole role) {

        return FlatMapUtil.flatMapMono(

                SecurityContextUtil::getUsersContextAuthentication,

                ca -> this.appService.getAppByCode(appCode),

                (ca, app) -> this.appService.hasWriteAccess(appCode, ca.getClientCode())
                        .filter(e -> e),

                (ca, app, hasWriteAccess) -> {

                    if (role.getClientId() == null) {
                        role.setClientId(ULong.valueOf(ca.getUser().getClientId()));
                        return Mono.just(true);
                    }

                    if (ca.isSystemClient()) {
                        Mono.just(true);
                    }

                    return this.clientService
                            .isBeingManagedBy(ULong.valueOf(ca.getUser().getClientId()),
                                    role.getClientId())
                            .filter(e -> e);
                },

                (ca, app, hasWriteAccess, isBeingManaged) -> this.dao.createRole(app, role)
                        .flatMap(this::fill)

        )
                .switchIfEmpty(this.messageService.throwMessage(
                        msg -> new GenericException(HttpStatus.FORBIDDEN, msg),
                        SecurityMessageResourceService.FORBIDDEN_APP_REG_OBJECTS,
                        "Role Access"))
                .contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.createRole"));
    }

    public Mono<AppRegistrationRole> getRoleById(ULong id) {
        return FlatMapUtil.flatMapMono(

                SecurityContextUtil::getUsersContextAuthentication,

                ca -> this.dao.getRoleById(id),

                (ca, role) -> ca.isSystemClient() ? Mono.just(true)
                        : this.appService
                                .hasWriteAccess(role.getAppId(),
                                        ULong.valueOf(ca.getUser()
                                                .getClientId()))
                                .filter(e -> e),

                (ca, role, hasAccess) -> ca.isSystemClient() ? Mono.just(true)
                        : this.clientService
                                .isBeingManagedBy(
                                        ULong.valueOf(ca.getUser()
                                                .getClientId()),
                                        role.getClientId())
                                .filter(e -> e),

                (ca, role, hasAccess, isBeingManaged) -> this.fill(role)

        ).contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.getRoleById"));
    }

    public Mono<Boolean> deleteRole(ULong id) {
        return FlatMapUtil.flatMapMono(

                () -> this.getRoleById(id),

                role -> this.dao.deleteRole(id)

        ).contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.deleteRole"));
    }

    public Mono<Page<AppRegistrationRole>> getRole(String appCode, String roleName, String clientCode,
            ULong clientId,
            String clientType, ClientLevelType level, String businessType, Pageable pageable) {

        if (clientCode != null && clientId != null) {
            return this.messageService.throwMessage(
                    msg -> new GenericException(HttpStatus.BAD_REQUEST, msg),
                    SecurityMessageResourceService.CLIENT_CODE_OR_ID_ONLY_ONE);
        }

        return FlatMapUtil.flatMapMono(

                SecurityContextUtil::getUsersContextAuthentication,

                ca -> this.appService.getAppByCode(appCode),

                (ca, app) -> ca.isSystemClient() ? Mono.just(true)
                        : this.appService.hasWriteAccess(appCode, ca.getClientCode())
                                .filter(e -> e),

                (ca, app, hasWriteAccess) -> {

                    if (ca.isSystemClient() || (clientId == null && clientCode == null)) {
                        return Mono.just(ULong.valueOf(ca.getUser().getClientId()));
                    }

                    return (clientCode != null
                            ? this.clientService.getClientBy(clientCode).map(e -> e.getId())
                            : Mono.just(clientId))
                            .flatMap(id -> this.clientService
                                    .isBeingManagedBy(
                                            ULong.valueOf(ca.getUser()
                                                    .getClientId()),
                                            id)
                                    .filter(e -> e)
                                    .map(e -> id));
                },

                (ca, app, hasWriteAccess, newClientId) -> {

                    Mono<Page<AppRegistrationRole>> page = this.dao.getRole(app.getId(), roleName,
                            newClientId,
                            clientType,
                            level, businessType, pageable);

                    return page.flatMap(e -> Flux.fromIterable(e.getContent())
                            .flatMap(this::fill).collectList().map(x -> e));
                }

        ).contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.getRole"));
    }

    private Mono<AppRegistrationRole> fill(AppRegistrationRole role) {

        return FlatMapUtil.flatMapMono(
                () -> this.appService.getAppById(role.getAppId()),

                app -> this.clientService.getClientInfoById(role.getClientId()),

                (app, client) -> this.roleService.read(role.getRoleId()),

                (app, client, roleInfo) -> Mono
                        .just((AppRegistrationRole) role.setRole(roleInfo).setApp(app)
                                .setClient(client))

        ).contextWrite(
                Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.fill(AppRegistrationRole)"));
    }

    public Mono<AppRegistrationIntegration> createRegIntegration(String appCode,
            AppRegistrationIntegration regIntegration) {

        return FlatMapUtil.flatMapMono(

                SecurityContextUtil::getUsersContextAuthentication,

                ca -> this.appService.getAppByCode(appCode),

                (ca, app) -> this.appService.hasWriteAccess(appCode, ca.getClientCode())
                        .filter(e -> e),

                (ca, app, hasWriteAccess) -> {

                    if (ca.isSystemClient()) {
                        return Mono.just(true);
                    }

                    return this.clientService
                            .isBeingManagedBy(ULong.valueOf(ca.getUser().getClientId()),
                                    regIntegration.getClientId())
                            .filter(e -> e);
                },

                (ca, app, hasWriteAccess, isBeingManaged) -> this.clientService
                        .hasIntegrationAccess(ULong.valueOf(ca.getUser().getClientId()),
                                regIntegration.getIntegrationId())
                        .filter(e -> e),

                (ca, app, hasWriteAccess, isBeingManaged, hasIntegrationAccess) -> this.dao
                        .createRegIntegration(app, regIntegration)

        )
                .switchIfEmpty(this.messageService.throwMessage(
                        msg -> new GenericException(HttpStatus.FORBIDDEN, msg),
                        SecurityMessageResourceService.FORBIDDEN_APP_REG_OBJECTS,
                        "Integration Access"))
                .contextWrite(Context.of(LogUtil.METHOD_NAME,
                        "AppRegistrationService.createRegIntegration"));
    }

    public Mono<Page<AppRegistrationIntegration>> getIntegration(String appCode, ULong clientId, String clientCode,
            Pageable pageable) {

        if (clientCode != null && clientId != null) {
            return this.messageService.throwMessage(
                    msg -> new GenericException(HttpStatus.BAD_REQUEST, msg),
                    SecurityMessageResourceService.CLIENT_CODE_OR_ID_ONLY_ONE);
        }

        return FlatMapUtil.flatMapMono(

                SecurityContextUtil::getUsersContextAuthentication,

                ca -> this.appService.getAppByCode(appCode),

                (ca, app) -> ca.isSystemClient() ? Mono.just(true)
                        : this.appService.hasWriteAccess(appCode, ca.getClientCode())
                                .filter(e -> e),

                (ca, app, hasWriteAccess) -> {

                    if (ca.isSystemClient() || (clientId == null && clientCode == null)) {
                        return Mono.just(ULong.valueOf(ca.getUser().getClientId()));
                    }

                    return (clientCode != null
                            ? this.clientService.getClientBy(clientCode).map(e -> e.getId())
                            : Mono.just(clientId))
                            .flatMap(id -> this.clientService
                                    .isBeingManagedBy(
                                            ULong.valueOf(ca.getUser()
                                                    .getClientId()),
                                            id)
                                    .filter(e -> e)
                                    .map(e -> id));
                },

                (ca, app, hasWriteAccess, cId) -> {

                    Mono<Page<AppRegistrationIntegration>> page = this.dao.getRegIntegration(app.getId(), cId,
                            pageable);

                    return page.flatMap(e -> Flux.fromIterable(e.getContent())
                            .flatMap(integration -> this.fill(appCode, integration)).collectList().map(x -> e));
                }

        ).contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.getIntegration"));
    }

    private Mono<AppRegistrationIntegration> fill(String appCode, AppRegistrationIntegration appRegIntg) {

        return FlatMapUtil.flatMapMono(

                () -> this.appService.getAppById(appRegIntg.getAppId()),

                app -> this.clientService.getClientInfoById(appRegIntg.getClientId()),

                (app, client) -> this.integrationService.read(appRegIntg.getIntegrationId()),

                (app, client, intgInfo) -> this.getIntegrationScopesByIntgId(appCode, appRegIntg.getClientId(),
                        null, appRegIntg.getIntegrationId()),

                (app, client, intgInfo, intgScopes) -> Mono
                        .just((AppRegistrationIntegration) appRegIntg.setIntegration(intgInfo)
                                .setIntegrationScopes(intgScopes)
                                .setApp(app)
                                .setClient(client))

        ).contextWrite(
                Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.fill(AppRegistrationIntegration)"));
    }

    private Mono<List<IntegrationScope>> getIntegrationScopesByIntgId(String appCode, ULong clientId,
            String clientCode, ULong integrationId) {

        return FlatMapUtil.flatMapMono(

                () -> this.getIntegrationScope(appCode, clientId, clientCode, integrationId),

                appRegIntgScopes -> Flux.fromIterable(appRegIntgScopes)
                        .flatMap(scope -> this.integrationScopeService.read(scope.getIntegrationScopeId()))
                        .collectList()

        ).contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.getIntegrationScope"));
    }

    public Mono<AppRegistrationIntegration> getRegIntegrationById(ULong id) {
        return FlatMapUtil.flatMapMono(

                SecurityContextUtil::getUsersContextAuthentication,

                ca -> this.dao.getRegIntegrationById(id),

                (ca, intg) -> ca.isSystemClient() ? Mono.just(true)
                        : this.appService
                                .hasWriteAccess(intg.getAppId(),
                                        ULong.valueOf(ca.getUser()
                                                .getClientId()))
                                .filter(e -> e),

                (ca, intg, hasAccess) -> ca.isSystemClient() ? Mono.just(true)
                        : this.clientService
                                .isBeingManagedBy(
                                        ULong.valueOf(ca.getUser()
                                                .getClientId()),
                                        intg.getClientId())
                                .filter(e -> e),

                (ca, intg, hasAccess, isBeingManaged) -> Mono.just(intg)

        ).contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.getRegIntegrationById"));
    }

    public Mono<Boolean> deleteRegIntegration(ULong id) {
        return FlatMapUtil.flatMapMono(

                () -> this.getRegIntegrationById(id),

                intg -> this.dao.deleteRegIntegration(id)

        ).contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.deleteRegIntegration"));
    }

    public Mono<AppRegistrationIntegrationScope> createRegIntegrationScope(String appCode,
            AppRegistrationIntegrationScope regIntegrationScope) {

        return FlatMapUtil.flatMapMono(

                SecurityContextUtil::getUsersContextAuthentication,

                ca -> this.appService.getAppByCode(appCode),

                (ca, app) -> this.appService.hasWriteAccess(appCode, ca.getClientCode())
                        .filter(e -> e),

                (ca, app, hasWriteAccess) -> {

                    if (ca.isSystemClient()) {
                        return Mono.just(true);
                    }

                    return this.clientService
                            .isBeingManagedBy(ULong.valueOf(ca.getUser().getClientId()),
                                    regIntegrationScope.getClientId())
                            .filter(e -> e);
                },

                (ca, app, hasWriteAccess, isBeingManaged) -> this.clientService
                        .hasIntegrationScopeAccess(ULong.valueOf(ca.getUser().getClientId()),
                                regIntegrationScope.getIntegrationScopeId())
                        .filter(e -> e),

                (ca, app, hasWriteAccess, isBeingManaged, hasIntegrationScopeAccess) -> this.dao
                        .createRegIntegrationScope(app, regIntegrationScope)

        )
                .switchIfEmpty(this.messageService.throwMessage(
                        msg -> new GenericException(HttpStatus.FORBIDDEN, msg),
                        SecurityMessageResourceService.FORBIDDEN_APP_REG_OBJECTS,
                        "Integration Access"))
                .contextWrite(Context.of(LogUtil.METHOD_NAME,
                        "AppRegistrationService.createRegIntegrationScope"));
    }

    public Mono<AppRegistrationIntegrationScope> getRegIntegrationScopeById(ULong id) {
        return FlatMapUtil.flatMapMono(

                SecurityContextUtil::getUsersContextAuthentication,

                ca -> this.dao.getRegIntegrationScopeById(id),

                (ca, intgScope) -> ca.isSystemClient() ? Mono.just(true)
                        : this.appService
                                .hasWriteAccess(intgScope.getAppId(),
                                        ULong.valueOf(ca.getUser()
                                                .getClientId()))
                                .filter(e -> e),

                (ca, intgScope, hasAccess) -> ca.isSystemClient() ? Mono.just(true)
                        : this.clientService
                                .isBeingManagedBy(
                                        ULong.valueOf(ca.getUser()
                                                .getClientId()),
                                        intgScope.getClientId())
                                .filter(e -> e),

                (ca, intgScope, hasAccess, isBeingManaged) -> Mono.just(intgScope)

        ).contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.getRegIntegrationScopeById"));
    }

    public Mono<Page<AppRegistrationIntegrationScope>> getIntegrationScope(String appCode, ULong clientId,
            String clientCode, ULong integrationId, Pageable pageable) {

        if (clientCode != null && clientId != null) {
            return this.messageService.throwMessage(
                    msg -> new GenericException(HttpStatus.BAD_REQUEST, msg),
                    SecurityMessageResourceService.CLIENT_CODE_OR_ID_ONLY_ONE);
        }

        return FlatMapUtil.flatMapMono(

                SecurityContextUtil::getUsersContextAuthentication,

                ca -> this.appService.getAppByCode(appCode),

                (ca, app) -> ca.isSystemClient() ? Mono.just(true)
                        : this.appService.hasWriteAccess(appCode, ca.getClientCode())
                                .filter(e -> e),

                (ca, app, hasWriteAccess) -> {

                    if (ca.isSystemClient() || (clientId == null && clientCode == null)) {
                        return Mono.just(ULong.valueOf(ca.getUser().getClientId()));
                    }

                    return (clientCode != null
                            ? this.clientService.getClientBy(clientCode).map(e -> e.getId())
                            : Mono.just(clientId))
                            .flatMap(id -> this.clientService
                                    .isBeingManagedBy(
                                            ULong.valueOf(ca.getUser()
                                                    .getClientId()),
                                            id)
                                    .filter(e -> e)
                                    .map(e -> id));
                },

                (ca, app, hasWriteAccess, cId) -> {

                    Mono<Page<AppRegistrationIntegrationScope>> page = this.dao.getRegIntegrationScope(app.getId(),
                            cId, integrationId, pageable);

                    return page.flatMap(e -> Flux.fromIterable(e.getContent())
                            .flatMap(this::fill).collectList().map(x -> e));
                }

        ).contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.getIntegration"));
    }

    private Mono<AppRegistrationIntegrationScope> fill(AppRegistrationIntegrationScope appRegIntgScope) {

        return FlatMapUtil.flatMapMono(
                () -> this.appService.getAppById(appRegIntgScope.getAppId()),

                app -> this.clientService.getClientInfoById(appRegIntgScope.getClientId()),

                (app, client) -> this.integrationScopeService.read(appRegIntgScope.getIntegrationScopeId()),

                (app, client, intgScopeInfo) -> this.integrationService.read(intgScopeInfo.getIntegrationId()),

                (app, client, intgScopeInfo, intgInfo) -> Mono
                        .just((AppRegistrationIntegrationScope) appRegIntgScope.setIntegrationScope(intgScopeInfo)
                                .setIntegration(intgInfo).setApp(app)
                                .setClient(client))

        ).contextWrite(
                Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.fill(AppRegistrationRole)"));
    }

    public Mono<List<AppRegistrationIntegrationScope>> getIntegrationScope(String appCode, ULong clientId,
            String clientCode, ULong integrationId) {

        if (clientCode != null && clientId != null) {
            return this.messageService.throwMessage(
                    msg -> new GenericException(HttpStatus.BAD_REQUEST, msg),
                    SecurityMessageResourceService.CLIENT_CODE_OR_ID_ONLY_ONE);
        }

        return FlatMapUtil.flatMapMono(

                SecurityContextUtil::getUsersContextAuthentication,

                ca -> this.appService.getAppByCode(appCode),

                (ca, app) -> ca.isSystemClient() ? Mono.just(true)
                        : this.appService.hasWriteAccess(appCode, ca.getClientCode())
                                .filter(e -> e),

                (ca, app, hasWriteAccess) -> {

                    if (ca.isSystemClient() || (clientId == null && clientCode == null)) {
                        return Mono.just(ULong.valueOf(ca.getUser().getClientId()));
                    }

                    return (clientCode != null
                            ? this.clientService.getClientBy(clientCode).map(e -> e.getId())
                            : Mono.just(clientId))
                            .flatMap(id -> this.clientService
                                    .isBeingManagedBy(
                                            ULong.valueOf(ca.getUser()
                                                    .getClientId()),
                                            id)
                                    .filter(e -> e)
                                    .map(e -> id));
                },

                (ca, app, hasWriteAccess, cId) -> this.dao.getRegIntegrationScope(app.getId(),
                        cId, integrationId)

        ).contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.getIntegration"));
    }

    public Mono<Boolean> deleteRegIntegrationScope(ULong id) {
        return FlatMapUtil.flatMapMono(

                () -> this.getRegIntegrationScopeById(id),

                intgScope -> this.dao.deleteRegIntegrationScope(id)

        ).contextWrite(Context.of(LogUtil.METHOD_NAME, "AppRegistrationService.deleteRegIntegration"));
    }
}