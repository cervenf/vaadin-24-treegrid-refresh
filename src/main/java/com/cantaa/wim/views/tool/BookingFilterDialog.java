package com.cantaa.wim.views.tool;

import com.cantaa.wim.dto.MainFilterDto;
import com.cantaa.wim.dto.TreeNode;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;
import lombok.Getter;

import static org.apache.commons.lang3.StringUtils.trimToNull;

public class BookingFilterDialog extends Dialog {


    private TreeNode treeNode;
    private final TextField name;

    public BookingFilterDialog(MainFilterDto mainFilterDto) {
        this.setHeaderTitle("Booking filter");

        name = new TextField("Name");

        var dialogLayout = new VerticalLayout(name);

        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

        this.add(dialogLayout);

        var saveButton = new Button("Save", e -> {
            mainFilterDto.setName(trimToNull(name.getValue()));
            fireEvent(new SaveEventFilterDialogEvent(this, treeNode));
            this.close();
        });
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        var cancelButton = new Button("Cancel", e -> this.close());

        this.getFooter().add(cancelButton);
        this.getFooter().add(saveButton);
        this.setResizable(true);
    }

    public void setTreeNode(TreeNode treeNode) {
        this.treeNode = treeNode;
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

    @Getter
    public static class SaveEventFilterDialogEvent extends ComponentEvent<BookingFilterDialog> {
        private final TreeNode treeNode;

        protected SaveEventFilterDialogEvent(BookingFilterDialog source, TreeNode treeNode) {
            super(source, false);
            this.treeNode = treeNode;
        }

    }

}
