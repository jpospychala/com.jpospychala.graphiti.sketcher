/**
 */
package com.jpospychala.graphiti.sketcher.model.sketcher;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.jpospychala.graphiti.sketcher.model.sketcher.SketcherFactory
 * @model kind="package"
 * @generated
 */
public interface SketcherPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "sketcher";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.jpospychala.com/sketcher/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "sk";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SketcherPackage eINSTANCE = com.jpospychala.graphiti.sketcher.model.sketcher.impl.SketcherPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.jpospychala.graphiti.sketcher.model.sketcher.impl.ModelObjectImpl <em>Model Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.jpospychala.graphiti.sketcher.model.sketcher.impl.ModelObjectImpl
	 * @see com.jpospychala.graphiti.sketcher.model.sketcher.impl.SketcherPackageImpl#getModelObject()
	 * @generated
	 */
	int MODEL_OBJECT = 0;

	/**
	 * The number of structural features of the '<em>Model Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OBJECT_FEATURE_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link com.jpospychala.graphiti.sketcher.model.sketcher.ModelObject <em>Model Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Object</em>'.
	 * @see com.jpospychala.graphiti.sketcher.model.sketcher.ModelObject
	 * @generated
	 */
	EClass getModelObject();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SketcherFactory getSketcherFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.jpospychala.graphiti.sketcher.model.sketcher.impl.ModelObjectImpl <em>Model Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.jpospychala.graphiti.sketcher.model.sketcher.impl.ModelObjectImpl
		 * @see com.jpospychala.graphiti.sketcher.model.sketcher.impl.SketcherPackageImpl#getModelObject()
		 * @generated
		 */
		EClass MODEL_OBJECT = eINSTANCE.getModelObject();

	}

} //SketcherPackage
