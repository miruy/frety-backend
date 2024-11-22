package me.frety.frety_back.domain.tab.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTab is a Querydsl query type for Tab
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTab extends EntityPathBase<Tab> {

    private static final long serialVersionUID = -1794855251L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTab tab = new QTab("tab");

    public final me.frety.frety_back.domain.common.entity.QBaseEntity _super = new me.frety.frety_back.domain.common.entity.QBaseEntity(this);

    public final StringPath artist = createString("artist");

    public final me.frety.frety_back.domain.account.entity.QAccount author;

    public final StringPath authorName = createString("authorName");

    public final StringPath capo = createString("capo");

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final ListPath<me.frety.frety_back.domain.Favorite.entity.Favorite, me.frety.frety_back.domain.Favorite.entity.QFavorite> favorites = this.<me.frety.frety_back.domain.Favorite.entity.Favorite, me.frety.frety_back.domain.Favorite.entity.QFavorite>createList("favorites", me.frety.frety_back.domain.Favorite.entity.Favorite.class, me.frety.frety_back.domain.Favorite.entity.QFavorite.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath song = createString("song");

    public final StringPath style = createString("style");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<me.frety.frety_back.domain.vote.entity.Vote, me.frety.frety_back.domain.vote.entity.QVote> votes = this.<me.frety.frety_back.domain.vote.entity.Vote, me.frety.frety_back.domain.vote.entity.QVote>createList("votes", me.frety.frety_back.domain.vote.entity.Vote.class, me.frety.frety_back.domain.vote.entity.QVote.class, PathInits.DIRECT2);

    public QTab(String variable) {
        this(Tab.class, forVariable(variable), INITS);
    }

    public QTab(Path<? extends Tab> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTab(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTab(PathMetadata metadata, PathInits inits) {
        this(Tab.class, metadata, inits);
    }

    public QTab(Class<? extends Tab> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new me.frety.frety_back.domain.account.entity.QAccount(forProperty("author"), inits.get("author")) : null;
    }

}

