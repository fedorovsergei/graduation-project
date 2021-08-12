package com.example.graduationproject.Entity;

import lombok.*;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Access(AccessType.FIELD)
@Data
public abstract class BaseAbstractEntityName extends BaseAbstractEntityId {

    @Column(name = "NAME")
    protected String name;
}
