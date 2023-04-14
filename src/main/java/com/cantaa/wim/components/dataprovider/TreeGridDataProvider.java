package com.cantaa.wim.components.dataprovider;

import com.cantaa.wim.dto.MainFilterDto;
import com.cantaa.wim.dto.TreeNode;
import com.vaadin.flow.data.provider.hierarchy.AbstractBackEndHierarchicalDataProvider;
import com.vaadin.flow.data.provider.hierarchy.HierarchicalQuery;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.helger.commons.string.StringHelper.hasText;

@RequiredArgsConstructor
public class TreeGridDataProvider extends AbstractBackEndHierarchicalDataProvider<TreeNode, Void> {

    private final MainFilterDto filter;
    private final transient DataService dataService;

    @Override
    protected Stream<TreeNode> fetchChildrenFromBackEnd(HierarchicalQuery<TreeNode, Void> query) {
        if (query.getParent() == null) {
            return dataService.getPerson().stream();
        }

        return dataService.getPerson(query.getParent().getId()).stream()
                .filter(filterByName());
    }

    @Override
    public boolean hasChildren(TreeNode node) {
        return !dataService.getPerson(node.getId()).stream().filter(filterByName()).toList().isEmpty();
    }

    @Override
    public int getChildCount(HierarchicalQuery<TreeNode, Void> query) {
        if (query.getParent() == null) {
            return dataService.getPerson().size();
        }

        return dataService.getPerson(query.getParent().getId()).stream().filter(filterByName()).toList().size();
    }

    private Predicate<TreeNode> filterByName() {
        return p -> {
            if (!hasText(filter.getName())) {
                return true;
            }
            return p.getName().equalsIgnoreCase(filter.getName());
        };
    }

}
