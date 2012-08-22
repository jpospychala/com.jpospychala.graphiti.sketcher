package com.jpospychala.graphiti.sketcher.internal.properties;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.StylesPackage;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColorCellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.graphics.RGB;

public class AttributeEditingSupport extends EditingSupport {

	private AbstractPropertySection section;

	public AttributeEditingSupport(ColumnViewer viewer, AbstractPropertySection abstractPropertySection) {
		super(viewer);
		this.section = abstractPropertySection;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		if (element instanceof EReference) {
			EReference entry = (EReference) element;
			int id = entry.getEType().getClassifierID();
			if (id == StylesPackage.COLOR) {
				return new ColorCellEditor(((TableViewer)getViewer()).getTable());
			}
		}
		return new TextCellEditor(((TableViewer)getViewer()).getTable());			
	}

	@Override
	protected boolean canEdit(Object element) {
		if (element instanceof EAttribute) {
			EAttribute entry = (EAttribute) element;
			int id = entry.getEType().getClassifierID();
			return (id == EcorePackage.EINT) || (id == EcorePackage.ESTRING) || (id == EcorePackage.EBOOLEAN) || (id == EcorePackage.EFLOAT) || (id == EcorePackage.EDOUBLE) ||
					(id == EcorePackage.EINTEGER_OBJECT) || (id == EcorePackage.EBOOLEAN_OBJECT) || (id == EcorePackage.EFLOAT_OBJECT) || (id == EcorePackage.EDOUBLE_OBJECT);
		} else if (element instanceof EReference) {
			EReference entry = (EReference) element;
			int id = entry.getEType().getClassifierID();
			return (id == StylesPackage.COLOR);
		}
		
		return false;
	}

	@Override
	protected Object getValue(Object element) {
		if (element instanceof EAttribute) {
			EAttribute entry = (EAttribute) element;
			EObject obj = (EObject) getViewer().getInput();
			Object value = obj.eGet(entry);
			return value != null ? value.toString() : "";
		} else if (element instanceof EReference) {
			EReference entry = (EReference) element;
			EObject obj = (EObject) getViewer().getInput();
			int id = entry.getEType().getClassifierID();
			
			if (id == StylesPackage.COLOR) {
				Color currColor = (Color) obj.eGet(entry);
				if (currColor != null) {
					return new RGB(currColor.getRed(), currColor.getGreen(), currColor.getBlue());
				} 
				return null;
			}
		}
		
		return "";
	}

	@Override
	protected void setValue(Object element, Object value) {
		Object newValue = null;
		EObject obj = (EObject) getViewer().getInput();
		TransactionalEditingDomain editingDomain = section.getDiagramEditor().getEditingDomain();
		
		if (element instanceof EAttribute) {
			EAttribute entry = (EAttribute) element;
			switch (entry.getEType().getClassifierID()) {
			case EcorePackage.EINT :
			case EcorePackage.EINTEGER_OBJECT:
				newValue = Integer.parseInt((String)value);
				break;
				
			case EcorePackage.ESTRING :
				newValue = value;
				break;
				
			case EcorePackage.EBOOLEAN :
			case EcorePackage.EBOOLEAN_OBJECT :
				newValue = Boolean.parseBoolean((String)value);
				break;
				
			case EcorePackage.EFLOAT :
			case EcorePackage.EFLOAT_OBJECT :
				newValue = Float.parseFloat((String)value);
				break;
				
			case EcorePackage.EDOUBLE :
			case EcorePackage.EDOUBLE_OBJECT :
				newValue = Double.parseDouble((String)value);
				break;
			}
		} else if (element instanceof EReference) {
			EReference entry = (EReference) element;
			int id = entry.getEType().getClassifierID();
			if (id == StylesPackage.COLOR) {
				final RGB newRGB = (RGB) value;
				if (newRGB == null) {
					return;
				}
				
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					
					@Override
					protected void doExecute() {
						Graphiti.getGaService().manageColor(section.getDiagramEditor().getDiagramTypeProvider().getDiagram(), newRGB.red, newRGB.green, newRGB.blue);
						
					}
				});
				
				Color newColor = Graphiti.getGaService().manageColor(section.getDiagramEditor().getDiagramTypeProvider().getDiagram(), newRGB.red, newRGB.green, newRGB.blue);
				newValue = newColor;
			}
		}
		
		if (newValue != null) {
			editingDomain.getCommandStack().execute(new SetCommand(editingDomain, obj, (EStructuralFeature)element, newValue));
			getViewer().update(element, null);
		}
		
	}

}
