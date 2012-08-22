package com.jpospychala.graphiti.sketcher.internal.properties;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.StylesPackage;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.IDiagramEditor;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class AbstractPropertySection extends GFPropertySection implements ITabbedPropertyConstants {

	 private TableViewer tv;
	 
	    @Override
	    public void createControls(Composite parent,
	        TabbedPropertySheetPage tabbedPropertySheetPage) {
	        super.createControls(parent, tabbedPropertySheetPage);
	 
	        TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
	        Composite composite = factory.createFlatFormComposite(parent);
	        parent.setLayout(new GridLayout(1, false));
	        composite.setLayout(new GridLayout(1, false));
	        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
	        
	        Table table = factory.createTable(composite, SWT.NONE);
	        GridData gd = new GridData(GridData.FILL_BOTH);
	        gd.grabExcessHorizontalSpace = true;
	        gd.grabExcessVerticalSpace = true;
	        table.setLayoutData(gd);
	        table.setHeaderVisible(true);
	        
	        tv = new TableViewer(table);
	        tv.setContentProvider(new IStructuredContentProvider() {
				
				public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
				}
				
				public void dispose() {
				}
				
				public Object[] getElements(Object inputElement) {
					GraphicsAlgorithm ga = (GraphicsAlgorithm)inputElement;
					EList<EAttribute> attrs = ga.eClass().getEAllAttributes();
					EList<EReference> refs = ga.eClass().getEAllReferences();
					Object[] out = new Object[attrs.size() + refs.size()];
					System.arraycopy(attrs.toArray(), 0, out, 0, attrs.size());
					System.arraycopy(refs.toArray(), 0, out, attrs.size(), refs.size());
					return out;
				}
			});
	        
	        TableViewerColumn names = new TableViewerColumn(tv, SWT.NONE);
	        names.getColumn().setText("Name");
	        names.getColumn().setWidth(200);
	        names.setLabelProvider(new ColumnLabelProvider() {
				@Override
				public String getText(Object element) {
					return ((ENamedElement) element).getName();
				}
			});
	        
	        TableViewerColumn values = new TableViewerColumn(tv, SWT.NONE);
	        values.getColumn().setText("Value");
	        values.getColumn().setWidth(200);
	        values.setLabelProvider(new ColumnLabelProvider() {
				@Override
				public String getText(Object element) {
					if (element instanceof EAttribute) {
						EAttribute entry = (EAttribute) element;
						EObject obj = (EObject) tv.getInput();
						Object value = obj.eGet(entry);
						return value != null ? value.toString() : "";						
					} else {
						return "";
					}
				}
				
				@Override
				public Color getBackground(Object element) {
					if (element instanceof EReference) {
						EReference entry = (EReference) element;
						int id = entry.getEType().getClassifierID();
						if (id == StylesPackage.COLOR) {
							EObject obj = (EObject) tv.getInput();
							org.eclipse.graphiti.mm.algorithms.styles.Color currColor = (org.eclipse.graphiti.mm.algorithms.styles.Color) obj.eGet(entry);
							if (currColor != null) {
								RGB rgb = new RGB(currColor.getRed(), currColor.getGreen(), currColor.getBlue());
								return new Color(Display.getDefault(), rgb);
							} 
						}
					}
					return super.getBackground(element);
				}
			});
	        
	        values.setEditingSupport(new AttributeEditingSupport(tv, this));
	    }
	 
	    @Override
	    public void refresh() {
	        PictogramElement pe = getSelectedPictogramElement();
	        if (pe != null) {
	            Object bo = Graphiti.getLinkService()
	                 .getBusinessObjectForLinkedPictogramElement(pe);
	            if (bo == null)
	                return;
	            
	            GraphicsAlgorithm ga = (GraphicsAlgorithm) bo;
	            
	            tv.setInput(ga);
	        }
	    }
	    
	    @Override
	    public IDiagramEditor getDiagramEditor() {
	    	return super.getDiagramEditor();
	    }

}
