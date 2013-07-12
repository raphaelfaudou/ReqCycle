package org.eclipse.reqcycle.ui.collectionspropseditor.internal.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;

class EEnumEditingSupport extends EditingSupport {

    private final TableViewer tableViewer;

    public EEnumEditingSupport(TableViewer tableViewer) {
        super(tableViewer);
        this.tableViewer = tableViewer;
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        if (element instanceof EEnumLiteral) {
            return new ComboBoxCellEditor(this.tableViewer.getTable(), this.getItems((EEnumLiteral) element));
        }
        return null;
    }

    private String[] getItems(final EEnumLiteral literal) {
        Collection<EEnumLiteral> literals = literal.getEEnum().getELiterals();
        List<String> items = new ArrayList<String>();
        for (EEnumLiteral l : literals) {
            items.add(l.getLiteral());
        }
        return items.toArray(new String[items.size()]);
    }

    @Override
    protected boolean canEdit(Object element) {
        return false;
    }

    @Override
    protected Object getValue(Object element) {
        if (element instanceof EEnumLiteral) {
            return element;
        }
        return null;
    }

    @Override
    protected void setValue(Object element, Object value) {
        // TODO Auto-generated method stub

    }

}
