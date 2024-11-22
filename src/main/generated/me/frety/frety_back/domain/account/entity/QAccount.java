package me.frety.frety_back.domain.account.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccount is a Querydsl query type for Account
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccount extends EntityPathBase<Account> {

    private static final long serialVersionUID = -398628179L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccount account = new QAccount("account");

    public final me.frety.frety_back.domain.common.entity.QBaseEntity _super = new me.frety.frety_back.domain.common.entity.QBaseEntity(this);

    public final SetPath<me.frety.frety_back.domain.auth.entity.Authentication, me.frety.frety_back.domain.auth.entity.QAuthentication> authentications = this.<me.frety.frety_back.domain.auth.entity.Authentication, me.frety.frety_back.domain.auth.entity.QAuthentication>createSet("authentications", me.frety.frety_back.domain.auth.entity.Authentication.class, me.frety.frety_back.domain.auth.entity.QAuthentication.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QPrivacy privacy;

    public final EnumPath<Role> role = createEnum("role", Role.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QAccount(String variable) {
        this(Account.class, forVariable(variable), INITS);
    }

    public QAccount(Path<? extends Account> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAccount(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAccount(PathMetadata metadata, PathInits inits) {
        this(Account.class, metadata, inits);
    }

    public QAccount(Class<? extends Account> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.privacy = inits.isInitialized("privacy") ? new QPrivacy(forProperty("privacy"), inits.get("privacy")) : null;
    }

}

