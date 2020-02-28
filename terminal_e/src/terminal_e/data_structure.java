package terminal_e;

import java.awt.GridLayout;
import javax.swing.JPanel;

public class data_structure{
	public JPanel panel;
	public GridLayout grid;

	public void set_grid(int row2, int col2, int hgap2, int vgap2) {
		grid.setRows(row2);
		grid.setColumns(col2);
		grid.setHgap(hgap2);
		grid.setVgap(vgap2);
	}
	public data_structure() {
		panel= new JPanel();
		grid= new GridLayout();
		panel.setLayout(grid);
	}
}