package jrs.mystorage.util.mapper;

public abstract class Mapper<TEntity, TDto> {

    public abstract TEntity toEntity(TDto dto);
    public abstract TDto toDto(TEntity entity);
}
