package me.frety.frety_back.domain.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuthentication is a Querydsl query type for Authentication
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuthentication extends EntityPathBase<Authentication> {

    private static final long serialVersionUID = -390783737L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAuthentication authentication = new QAuthentication("authentication");

    public final me.frety.frety_back.domain.common.entity.QBaseEntity _super = new me.frety.frety_back.domain.common.entity.QBaseEntity(this);

    public final me.frety.frety_back.domain.account.entity.QAccount account;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath loginId = createString("loginId");

    public final StringPath password = createString("password");

    public final ListPath<Token, QToken> tokens = this.<Token, QToken>createList("tokens", Token.class, QToken.class, PathInits.DIRECT2);

    public final EnumPath<AuthenticationType> type = createEnum("type", AuthenticationType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QAuthentication(String variable) {
        this(Authentication.class, forVariable(variable), INITS);
    }

    public QAuthentication(Path<? extends Authentication> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAuthentication(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAuthentication(PathMetadata metadata, PathInits inits) {
        this(Authentication.class, metadata, inits);
    }

    public QAuthentication(Class<? extends Authentication> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.account = inits.isInitialized("account") ? new me.frety.frety_back.domain.account.entity.QAccount(forProperty("account"), inits.get("account")) : null;
    }

}

