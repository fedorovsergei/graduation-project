package com.example.graduationproject.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Access(AccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseAbstractEntityName extends BaseAbstractEntityId {

    @Column(name = "NAME")
    protected String name;
}