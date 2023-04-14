package com.cantaa.wim.views.tool;

import com.cantaa.wim.components.dataprovider.DataService;
import com.cantaa.wim.components.dataprovider.TreeGridDataProvider;
import com.cantaa.wim.dto.MainFilterDto;
import com.cantaa.wim.dto.TreeNode;
import com.cantaa.wim.views.MainLayout;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import lombok.extern.slf4j.Slf4j;

@PageTitle("tools-tree-grid")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "tools-tree-grid", layout = MainLayout.class)
@Uses(Icon.class)
@Slf4j
public class ToolsTreeGridView extends Div {

    private final TreeGrid<TreeNode> treeGrid;


    public ToolsTreeGridView(DataService dataService) {
        setHeightFull();

        treeGrid = new TreeGrid<>();
        treeGrid.setHeightFull();
        treeGrid.addHierarchyColumn(TreeNode::getName).setAutoWidth(true);


        MainFilterDto mainFilterDto = new MainFilterDto();

        var provider = new TreeGridDataProvider(mainFilterDto, dataService);

        treeGrid.setDataProvider(provider);


        var bookingFilterDialog = new BookingFilterDialog(mainFilterDto);

        var contextMenu = treeGrid.addContextMenu();

        contextMenu.addItem("filter", event -> {

            if (event.getItem().isEmpty()) {
                return;
            }

            var treeNode = event.getItem().get();

            treeGrid.select(treeNode);
            bookingFilterDialog.setTreeNode(treeNode);
            bookingFilterDialog.open();
        });

        var mainLayout = new VerticalLayout(treeGrid);
        mainLayout.setHeightFull();
        mainLayout.setWidthFull();

        add(mainLayout, bookingFilterDialog);

        bookingFilterDialog.addListener(BookingFilterDialog.SaveEventFilterDialogEvent.class, event -> {
            var treeNode = event.getTreeNode();

            if (treeNode == null) {
                return;
            }

            treeGrid.getDataProvider().refreshItem(treeNode, true);
        });
    }

}
