/**
 */
package com.jpospychala.graphiti.sketcher.model.sketcher;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.jpospychala.graphiti.sketcher.model.sketcher.SketcherPackage
 * @generated
 */
public interface SketcherFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SketcherFactory eINSTANCE = com.jpospychala.graphiti.sketcher.model.sketcher.impl.SketcherFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Model Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Object</em>'.
	 * @generated
	 */
	ModelObject createModelObject();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SketcherPackage getSketcherPackage();

} //SketcherFactory
