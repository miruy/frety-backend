package me.frety.frety_back.domain.vote.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVote is a Querydsl query type for Vote
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVote extends EntityPathBase<Vote> {

    private static final long serialVersionUID = -198424965L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVote vote = new QVote("vote");

    public final me.frety.frety_back.domain.common.entity.QBaseEntity _super = new me.frety.frety_back.domain.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final me.frety.frety_back.domain.tab.entity.QTab tab;

    public final EnumPath<VoteType> type = createEnum("type", VoteType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final me.frety.frety_back.domain.account.entity.QAccount voter;

    public QVote(String variable) {
        this(Vote.class, forVariable(variable), INITS);
    }

    public QVote(Path<? extends Vote> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVote(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVote(PathMetadata metadata, PathInits inits) {
        this(Vote.class, metadata, inits);
    }

    public QVote(Class<? extends Vote> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tab = inits.isInitialized("tab") ? new me.frety.frety_back.domain.tab.entity.QTab(forProperty("tab"), inits.get("tab")) : null;
        this.voter = inits.isInitialized("voter") ? new me.frety.frety_back.domain.account.entity.QAccount(forProperty("voter"), inits.get("voter")) : null;
    }

}

