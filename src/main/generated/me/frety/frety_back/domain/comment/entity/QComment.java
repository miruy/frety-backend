package me.frety.frety_back.domain.comment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = 858144877L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComment comment = new QComment("comment");

    public final me.frety.frety_back.domain.common.entity.QBaseEntity _super = new me.frety.frety_back.domain.common.entity.QBaseEntity(this);

    public final me.frety.frety_back.domain.account.entity.QAccount author;

    public final ListPath<Comment, QComment> childComments = this.<Comment, QComment>createList("childComments", Comment.class, QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QComment parentComment;

    public final me.frety.frety_back.domain.tab.entity.QTab tab;

    public final EnumPath<CommentType> type = createEnum("type", CommentType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath userName = createString("userName");

    public QComment(String variable) {
        this(Comment.class, forVariable(variable), INITS);
    }

    public QComment(Path<? extends Comment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComment(PathMetadata metadata, PathInits inits) {
        this(Comment.class, metadata, inits);
    }

    public QComment(Class<? extends Comment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new me.frety.frety_back.domain.account.entity.QAccount(forProperty("author"), inits.get("author")) : null;
        this.parentComment = inits.isInitialized("parentComment") ? new QComment(forProperty("parentComment"), inits.get("parentComment")) : null;
        this.tab = inits.isInitialized("tab") ? new me.frety.frety_back.domain.tab.entity.QTab(forProperty("tab"), inits.get("tab")) : null;
    }

}

