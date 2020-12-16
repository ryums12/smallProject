package com.ryums.bookmark.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMarkEntity is a Querydsl query type for MarkEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMarkEntity extends EntityPathBase<MarkEntity> {

    private static final long serialVersionUID = -174216918L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMarkEntity markEntity = new QMarkEntity("markEntity");

    public final QTimeEntity _super = new QTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> chgDt = _super.chgDt;

    public final NumberPath<Long> markIdx = createNumber("markIdx", Long.class);

    public final StringPath markTitle = createString("markTitle");

    public final StringPath markUrl = createString("markUrl");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    public final QTagEntity tagEntity;

    public final NumberPath<Long> tagIdx = createNumber("tagIdx", Long.class);

    public final StringPath useYn = createString("useYn");

    public QMarkEntity(String variable) {
        this(MarkEntity.class, forVariable(variable), INITS);
    }

    public QMarkEntity(Path<? extends MarkEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMarkEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMarkEntity(PathMetadata metadata, PathInits inits) {
        this(MarkEntity.class, metadata, inits);
    }

    public QMarkEntity(Class<? extends MarkEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tagEntity = inits.isInitialized("tagEntity") ? new QTagEntity(forProperty("tagEntity")) : null;
    }

}

