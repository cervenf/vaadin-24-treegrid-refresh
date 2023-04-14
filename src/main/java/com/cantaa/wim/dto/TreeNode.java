package com.cantaa.wim.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


@AllArgsConstructor
@Getter
@Setter
public class TreeNode implements Serializable {
    private String uuid;
    private Long id;
    private String name;

    public TreeNode(Long id, String name) {
        this.uuid = UUID.randomUUID().toString();
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return uuid.equals(treeNode.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
