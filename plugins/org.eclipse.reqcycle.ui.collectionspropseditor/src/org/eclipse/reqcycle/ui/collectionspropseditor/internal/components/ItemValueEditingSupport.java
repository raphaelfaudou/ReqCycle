package org.eclipse.reqcycle.ui.collectionspropseditor.internal.components;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

class ItemValueEditingSupport extends EditingSupport {

    private final TableViewer tableViewer;

    public ItemValueEditingSupport(TableViewer tableViewer) {
        super(tableViewer);
        this.tableViewer = tableViewer;
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        return new TextCellEditor(this.tableViewer.getTable());
    }

    @Override
    protected boolean canEdit(Object element) {
        return (element instanceof ItemValue);
    }

    @Override
    protected Object getValue(Object element) {
        return ((ItemValue<?>) element).getValue();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    protected void setValue(Object element, Object value) {
        ItemValue itemValue = (ItemValue) element;
        itemValue.setValue(value);
        this.tableViewer.update(element, null);
    }

}
