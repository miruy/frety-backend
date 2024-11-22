package me.frety.frety_back.domain.account.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPrivacy is a Querydsl query type for Privacy
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPrivacy extends EntityPathBase<Privacy> {

    private static final long serialVersionUID = 464192520L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPrivacy privacy = new QPrivacy("privacy");

    public final me.frety.frety_back.domain.common.entity.QBaseEntity _super = new me.frety.frety_back.domain.common.entity.QBaseEntity(this);

    public final QAccount account;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPrivacy(String variable) {
        this(Privacy.class, forVariable(variable), INITS);
    }

    public QPrivacy(Path<? extends Privacy> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPrivacy(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPrivacy(PathMetadata metadata, PathInits inits) {
        this(Privacy.class, metadata, inits);
    }

    public QPrivacy(Class<? extends Privacy> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.account = inits.isInitialized("account") ? new QAccount(forProperty("account"), inits.get("account")) : null;
    }

}

