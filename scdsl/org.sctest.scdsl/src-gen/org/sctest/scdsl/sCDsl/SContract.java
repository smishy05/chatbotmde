/**
 * generated by Xtext 2.22.0
 */
package org.sctest.scdsl.sCDsl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SContract</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.sctest.scdsl.sCDsl.SContract#getName <em>Name</em>}</li>
 *   <li>{@link org.sctest.scdsl.sCDsl.SContract#getPlatform <em>Platform</em>}</li>
 *   <li>{@link org.sctest.scdsl.sCDsl.SContract#getElement <em>Element</em>}</li>
 * </ul>
 *
 * @see org.sctest.scdsl.sCDsl.SCDslPackage#getSContract()
 * @model
 * @generated
 */
public interface SContract extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.sctest.scdsl.sCDsl.SCDslPackage#getSContract_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.sctest.scdsl.sCDsl.SContract#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Platform</b></em>' attribute.
   * The literals are from the enumeration {@link org.sctest.scdsl.sCDsl.TargetPlatform}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Platform</em>' attribute.
   * @see org.sctest.scdsl.sCDsl.TargetPlatform
   * @see #setPlatform(TargetPlatform)
   * @see org.sctest.scdsl.sCDsl.SCDslPackage#getSContract_Platform()
   * @model
   * @generated
   */
  TargetPlatform getPlatform();

  /**
   * Sets the value of the '{@link org.sctest.scdsl.sCDsl.SContract#getPlatform <em>Platform</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Platform</em>' attribute.
   * @see org.sctest.scdsl.sCDsl.TargetPlatform
   * @see #getPlatform()
   * @generated
   */
  void setPlatform(TargetPlatform value);

  /**
   * Returns the value of the '<em><b>Element</b></em>' containment reference list.
   * The list contents are of type {@link org.sctest.scdsl.sCDsl.Element}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Element</em>' containment reference list.
   * @see org.sctest.scdsl.sCDsl.SCDslPackage#getSContract_Element()
   * @model containment="true"
   * @generated
   */
  EList<Element> getElement();

} // SContract
