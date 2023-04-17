package com.cantaa.wim.components.dataprovider;

import com.cantaa.wim.dto.TreeNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public class DataService {


    public List<TreeNode> getPerson() {
        return List.of(
                new TreeNode(1L, "Name1"),
                new TreeNode(2L, "Name2"),
                new TreeNode(3L, "Name3")
                );
    }

    public List<TreeNode> getPerson(Long id) {
        return switch (id.toString()) {
            case "1" -> List.of(
                    new TreeNode(11L, "A"),
                    new TreeNode(12L, "B"),
                    new TreeNode(13L, "C"),
                    new TreeNode(14L, "D")
            );
            case "2" -> List.of(
                    new TreeNode(21L, "E"),
                    new TreeNode(22L, "F"),
                    new TreeNode(23L, "G"),
                    new TreeNode(24L, "H")
            );
            case "3" -> List.of(
                    new TreeNode(31L, "I"),
                    new TreeNode(32L, "J"),
                    new TreeNode(33L, "K"),
                    new TreeNode(34L, "L")
            );
            default -> emptyList();
        };
    }


}
