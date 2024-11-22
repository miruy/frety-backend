package me.frety.frety_back.domain.Favorite.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFavorite is a Querydsl query type for Favorite
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFavorite extends EntityPathBase<Favorite> {

    private static final long serialVersionUID = -1053143425L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFavorite favorite = new QFavorite("favorite");

    public final me.frety.frety_back.domain.common.entity.QBaseEntity _super = new me.frety.frety_back.domain.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final me.frety.frety_back.domain.account.entity.QAccount favoriter;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final me.frety.frety_back.domain.tab.entity.QTab tab;

    public final EnumPath<FavoriteType> type = createEnum("type", FavoriteType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QFavorite(String variable) {
        this(Favorite.class, forVariable(variable), INITS);
    }

    public QFavorite(Path<? extends Favorite> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFavorite(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFavorite(PathMetadata metadata, PathInits inits) {
        this(Favorite.class, metadata, inits);
    }

    public QFavorite(Class<? extends Favorite> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.favoriter = inits.isInitialized("favoriter") ? new me.frety.frety_back.domain.account.entity.QAccount(forProperty("favoriter"), inits.get("favoriter")) : null;
        this.tab = inits.isInitialized("tab") ? new me.frety.frety_back.domain.tab.entity.QTab(forProperty("tab"), inits.get("tab")) : null;
    }

}

