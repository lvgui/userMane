package com.chinasoft.view;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
//������ʽ������ݲ����޸ã����Ǹ�����ɫ
class Myedit extends AbstractCellEditor implements TableCellEditor {

public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
return this.getTableCellEditorComponent(table, value, isSelected, row, column);
}

public Object getCellEditorValue() {
return null;
}

public boolean isCellEditable(EventObject e) {
return false;
}

}