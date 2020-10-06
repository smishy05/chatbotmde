/**
 * generated by Xtext 2.22.0
 */
package org.sctest.scdsl.sCDsl.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.sctest.scdsl.sCDsl.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SCDslFactoryImpl extends EFactoryImpl implements SCDslFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static SCDslFactory init()
  {
    try
    {
      SCDslFactory theSCDslFactory = (SCDslFactory)EPackage.Registry.INSTANCE.getEFactory(SCDslPackage.eNS_URI);
      if (theSCDslFactory != null)
      {
        return theSCDslFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new SCDslFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SCDslFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case SCDslPackage.SCONTRACT: return createSContract();
      case SCDslPackage.ELEMENT: return createElement();
      case SCDslPackage.COMPOSITE_ELEMENT: return createCompositeElement();
      case SCDslPackage.PARTICIPANT: return createParticipant();
      case SCDslPackage.ASSET: return createAsset();
      case SCDslPackage.PARAMETER: return createParameter();
      case SCDslPackage.TRANSACTION: return createTransaction();
      case SCDslPackage.CONDITION: return createCondition();
      case SCDslPackage.RELATIONSHIP: return createRelationship();
      case SCDslPackage.EVENT: return createEvent();
      case SCDslPackage.TRAN_REL: return createTranRel();
      case SCDslPackage.ASSET_REL: return createAssetRel();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case SCDslPackage.TARGET_PLATFORM:
        return createTargetPlatformFromString(eDataType, initialValue);
      case SCDslPackage.PAR_TYPE:
        return createParTypeFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case SCDslPackage.TARGET_PLATFORM:
        return convertTargetPlatformToString(eDataType, instanceValue);
      case SCDslPackage.PAR_TYPE:
        return convertParTypeToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public SContract createSContract()
  {
    SContractImpl sContract = new SContractImpl();
    return sContract;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Element createElement()
  {
    ElementImpl element = new ElementImpl();
    return element;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public CompositeElement createCompositeElement()
  {
    CompositeElementImpl compositeElement = new CompositeElementImpl();
    return compositeElement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Participant createParticipant()
  {
    ParticipantImpl participant = new ParticipantImpl();
    return participant;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Asset createAsset()
  {
    AssetImpl asset = new AssetImpl();
    return asset;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Parameter createParameter()
  {
    ParameterImpl parameter = new ParameterImpl();
    return parameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Transaction createTransaction()
  {
    TransactionImpl transaction = new TransactionImpl();
    return transaction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Condition createCondition()
  {
    ConditionImpl condition = new ConditionImpl();
    return condition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Relationship createRelationship()
  {
    RelationshipImpl relationship = new RelationshipImpl();
    return relationship;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Event createEvent()
  {
    EventImpl event = new EventImpl();
    return event;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public TranRel createTranRel()
  {
    TranRelImpl tranRel = new TranRelImpl();
    return tranRel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public AssetRel createAssetRel()
  {
    AssetRelImpl assetRel = new AssetRelImpl();
    return assetRel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TargetPlatform createTargetPlatformFromString(EDataType eDataType, String initialValue)
  {
    TargetPlatform result = TargetPlatform.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertTargetPlatformToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ParType createParTypeFromString(EDataType eDataType, String initialValue)
  {
    ParType result = ParType.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertParTypeToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public SCDslPackage getSCDslPackage()
  {
    return (SCDslPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static SCDslPackage getPackage()
  {
    return SCDslPackage.eINSTANCE;
  }

} //SCDslFactoryImpl
