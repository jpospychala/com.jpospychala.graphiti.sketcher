/**
 */
package com.jpospychala.graphiti.sketcher.model.sketcher.impl;

import com.jpospychala.graphiti.sketcher.model.sketcher.ModelObject;
import com.jpospychala.graphiti.sketcher.model.sketcher.SketcherFactory;
import com.jpospychala.graphiti.sketcher.model.sketcher.SketcherPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SketcherPackageImpl extends EPackageImpl implements SketcherPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelObjectEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.jpospychala.graphiti.sketcher.model.sketcher.SketcherPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SketcherPackageImpl() {
		super(eNS_URI, SketcherFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link SketcherPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SketcherPackage init() {
		if (isInited) return (SketcherPackage)EPackage.Registry.INSTANCE.getEPackage(SketcherPackage.eNS_URI);

		// Obtain or create and register package
		SketcherPackageImpl theSketcherPackage = (SketcherPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SketcherPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SketcherPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theSketcherPackage.createPackageContents();

		// Initialize created meta-data
		theSketcherPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSketcherPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SketcherPackage.eNS_URI, theSketcherPackage);
		return theSketcherPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelObject() {
		return modelObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SketcherFactory getSketcherFactory() {
		return (SketcherFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		modelObjectEClass = createEClass(MODEL_OBJECT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(modelObjectEClass, ModelObject.class, "ModelObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //SketcherPackageImpl
