/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.product.payment;

import java.io.Serializable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.ImmutableValidator;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.strata.basics.PayReceive;
import com.opengamma.strata.basics.currency.Currency;
import com.opengamma.strata.basics.currency.CurrencyAmount;
import com.opengamma.strata.basics.currency.Payment;
import com.opengamma.strata.basics.date.AdjustableDate;
import com.opengamma.strata.product.Product;

/**
 * A bullet payment.
 * <p>
 * An Over-The-Counter (OTC) trade where one party makes a payment to another.
 * The reason for the payment is not captured.
 */
@BeanDefinition
public final class BulletPayment
    implements Product, ImmutableBean, Serializable {

  /**
   * Whether the payment is to be paid or received.
   * <p>
   * A value of 'Pay' implies that the amount is paid to the counterparty.
   * A value of 'Receive' implies that the amount is received from the counterparty.
   */
  @PropertyDefinition(validate = "notNull")
  private final PayReceive payReceive;
  /**
   * The amount of the payment.
   * <p>
   * The amount is unsigned, with the direction implied by {@code payReceive}.
   */
  @PropertyDefinition(validate = "notNull")
  private final CurrencyAmount value;
  /**
   * The date that the payment is made.
   * <p>
   * This date should normally be a valid business day.
   */
  @PropertyDefinition(validate = "notNull")
  private final AdjustableDate date;

  //-------------------------------------------------------------------------
  @ImmutableValidator
  private void validate() {
    if (value.getAmount() < 0) {
      throw new IllegalArgumentException("Amount must be unsigned");
    }
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the currency of this payment.
   * 
   * @return the payment currency
   */
  public Currency getCurrency() {
    return value.getCurrency();
  }

  //-------------------------------------------------------------------------
  /**
   * Expands this bullet payment into a {@code Payment}.
   * 
   * @return the payment
   */
  public Payment expandToPayment() {
    CurrencyAmount signed = payReceive == PayReceive.PAY ? value.negated() : value;
    return Payment.of(signed, date.adjusted());
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code BulletPayment}.
   * @return the meta-bean, not null
   */
  public static BulletPayment.Meta meta() {
    return BulletPayment.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(BulletPayment.Meta.INSTANCE);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static BulletPayment.Builder builder() {
    return new BulletPayment.Builder();
  }

  private BulletPayment(
      PayReceive payReceive,
      CurrencyAmount value,
      AdjustableDate date) {
    JodaBeanUtils.notNull(payReceive, "payReceive");
    JodaBeanUtils.notNull(value, "value");
    JodaBeanUtils.notNull(date, "date");
    this.payReceive = payReceive;
    this.value = value;
    this.date = date;
    validate();
  }

  @Override
  public BulletPayment.Meta metaBean() {
    return BulletPayment.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets whether the payment is to be paid or received.
   * <p>
   * A value of 'Pay' implies that the amount is paid to the counterparty.
   * A value of 'Receive' implies that the amount is received from the counterparty.
   * @return the value of the property, not null
   */
  public PayReceive getPayReceive() {
    return payReceive;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the amount of the payment.
   * <p>
   * The amount is unsigned, with the direction implied by {@code payReceive}.
   * @return the value of the property, not null
   */
  public CurrencyAmount getValue() {
    return value;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the date that the payment is made.
   * <p>
   * This date should normally be a valid business day.
   * @return the value of the property, not null
   */
  public AdjustableDate getDate() {
    return date;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      BulletPayment other = (BulletPayment) obj;
      return JodaBeanUtils.equal(payReceive, other.payReceive) &&
          JodaBeanUtils.equal(value, other.value) &&
          JodaBeanUtils.equal(date, other.date);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(payReceive);
    hash = hash * 31 + JodaBeanUtils.hashCode(value);
    hash = hash * 31 + JodaBeanUtils.hashCode(date);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(128);
    buf.append("BulletPayment{");
    buf.append("payReceive").append('=').append(payReceive).append(',').append(' ');
    buf.append("value").append('=').append(value).append(',').append(' ');
    buf.append("date").append('=').append(JodaBeanUtils.toString(date));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code BulletPayment}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code payReceive} property.
     */
    private final MetaProperty<PayReceive> payReceive = DirectMetaProperty.ofImmutable(
        this, "payReceive", BulletPayment.class, PayReceive.class);
    /**
     * The meta-property for the {@code value} property.
     */
    private final MetaProperty<CurrencyAmount> value = DirectMetaProperty.ofImmutable(
        this, "value", BulletPayment.class, CurrencyAmount.class);
    /**
     * The meta-property for the {@code date} property.
     */
    private final MetaProperty<AdjustableDate> date = DirectMetaProperty.ofImmutable(
        this, "date", BulletPayment.class, AdjustableDate.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "payReceive",
        "value",
        "date");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -885469925:  // payReceive
          return payReceive;
        case 111972721:  // value
          return value;
        case 3076014:  // date
          return date;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BulletPayment.Builder builder() {
      return new BulletPayment.Builder();
    }

    @Override
    public Class<? extends BulletPayment> beanType() {
      return BulletPayment.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code payReceive} property.
     * @return the meta-property, not null
     */
    public MetaProperty<PayReceive> payReceive() {
      return payReceive;
    }

    /**
     * The meta-property for the {@code value} property.
     * @return the meta-property, not null
     */
    public MetaProperty<CurrencyAmount> value() {
      return value;
    }

    /**
     * The meta-property for the {@code date} property.
     * @return the meta-property, not null
     */
    public MetaProperty<AdjustableDate> date() {
      return date;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -885469925:  // payReceive
          return ((BulletPayment) bean).getPayReceive();
        case 111972721:  // value
          return ((BulletPayment) bean).getValue();
        case 3076014:  // date
          return ((BulletPayment) bean).getDate();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code BulletPayment}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<BulletPayment> {

    private PayReceive payReceive;
    private CurrencyAmount value;
    private AdjustableDate date;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(BulletPayment beanToCopy) {
      this.payReceive = beanToCopy.getPayReceive();
      this.value = beanToCopy.getValue();
      this.date = beanToCopy.getDate();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -885469925:  // payReceive
          return payReceive;
        case 111972721:  // value
          return value;
        case 3076014:  // date
          return date;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -885469925:  // payReceive
          this.payReceive = (PayReceive) newValue;
          break;
        case 111972721:  // value
          this.value = (CurrencyAmount) newValue;
          break;
        case 3076014:  // date
          this.date = (AdjustableDate) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public BulletPayment build() {
      return new BulletPayment(
          payReceive,
          value,
          date);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets whether the payment is to be paid or received.
     * <p>
     * A value of 'Pay' implies that the amount is paid to the counterparty.
     * A value of 'Receive' implies that the amount is received from the counterparty.
     * @param payReceive  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder payReceive(PayReceive payReceive) {
      JodaBeanUtils.notNull(payReceive, "payReceive");
      this.payReceive = payReceive;
      return this;
    }

    /**
     * Sets the amount of the payment.
     * <p>
     * The amount is unsigned, with the direction implied by {@code payReceive}.
     * @param value  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder value(CurrencyAmount value) {
      JodaBeanUtils.notNull(value, "value");
      this.value = value;
      return this;
    }

    /**
     * Sets the date that the payment is made.
     * <p>
     * This date should normally be a valid business day.
     * @param date  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder date(AdjustableDate date) {
      JodaBeanUtils.notNull(date, "date");
      this.date = date;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(128);
      buf.append("BulletPayment.Builder{");
      buf.append("payReceive").append('=').append(JodaBeanUtils.toString(payReceive)).append(',').append(' ');
      buf.append("value").append('=').append(JodaBeanUtils.toString(value)).append(',').append(' ');
      buf.append("date").append('=').append(JodaBeanUtils.toString(date));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}