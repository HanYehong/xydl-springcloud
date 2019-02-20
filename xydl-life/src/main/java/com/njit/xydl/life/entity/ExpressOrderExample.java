package com.njit.xydl.life.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExpressOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExpressOrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        protected void addCriterionForJDBCTime(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value.getTime()), property);
        }

        protected void addCriterionForJDBCTime(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Time> timeList = new ArrayList<java.sql.Time>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                timeList.add(new java.sql.Time(iter.next().getTime()));
            }
            addCriterion(condition, timeList, property);
        }

        protected void addCriterionForJDBCTime(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value1.getTime()), new java.sql.Time(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andKdSizeIsNull() {
            addCriterion("kd_size is null");
            return (Criteria) this;
        }

        public Criteria andKdSizeIsNotNull() {
            addCriterion("kd_size is not null");
            return (Criteria) this;
        }

        public Criteria andKdSizeEqualTo(String value) {
            addCriterion("kd_size =", value, "kdSize");
            return (Criteria) this;
        }

        public Criteria andKdSizeNotEqualTo(String value) {
            addCriterion("kd_size <>", value, "kdSize");
            return (Criteria) this;
        }

        public Criteria andKdSizeGreaterThan(String value) {
            addCriterion("kd_size >", value, "kdSize");
            return (Criteria) this;
        }

        public Criteria andKdSizeGreaterThanOrEqualTo(String value) {
            addCriterion("kd_size >=", value, "kdSize");
            return (Criteria) this;
        }

        public Criteria andKdSizeLessThan(String value) {
            addCriterion("kd_size <", value, "kdSize");
            return (Criteria) this;
        }

        public Criteria andKdSizeLessThanOrEqualTo(String value) {
            addCriterion("kd_size <=", value, "kdSize");
            return (Criteria) this;
        }

        public Criteria andKdSizeLike(String value) {
            addCriterion("kd_size like", value, "kdSize");
            return (Criteria) this;
        }

        public Criteria andKdSizeNotLike(String value) {
            addCriterion("kd_size not like", value, "kdSize");
            return (Criteria) this;
        }

        public Criteria andKdSizeIn(List<String> values) {
            addCriterion("kd_size in", values, "kdSize");
            return (Criteria) this;
        }

        public Criteria andKdSizeNotIn(List<String> values) {
            addCriterion("kd_size not in", values, "kdSize");
            return (Criteria) this;
        }

        public Criteria andKdSizeBetween(String value1, String value2) {
            addCriterion("kd_size between", value1, value2, "kdSize");
            return (Criteria) this;
        }

        public Criteria andKdSizeNotBetween(String value1, String value2) {
            addCriterion("kd_size not between", value1, value2, "kdSize");
            return (Criteria) this;
        }

        public Criteria andKdDescriptionIsNull() {
            addCriterion("kd_description is null");
            return (Criteria) this;
        }

        public Criteria andKdDescriptionIsNotNull() {
            addCriterion("kd_description is not null");
            return (Criteria) this;
        }

        public Criteria andKdDescriptionEqualTo(String value) {
            addCriterion("kd_description =", value, "kdDescription");
            return (Criteria) this;
        }

        public Criteria andKdDescriptionNotEqualTo(String value) {
            addCriterion("kd_description <>", value, "kdDescription");
            return (Criteria) this;
        }

        public Criteria andKdDescriptionGreaterThan(String value) {
            addCriterion("kd_description >", value, "kdDescription");
            return (Criteria) this;
        }

        public Criteria andKdDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("kd_description >=", value, "kdDescription");
            return (Criteria) this;
        }

        public Criteria andKdDescriptionLessThan(String value) {
            addCriterion("kd_description <", value, "kdDescription");
            return (Criteria) this;
        }

        public Criteria andKdDescriptionLessThanOrEqualTo(String value) {
            addCriterion("kd_description <=", value, "kdDescription");
            return (Criteria) this;
        }

        public Criteria andKdDescriptionLike(String value) {
            addCriterion("kd_description like", value, "kdDescription");
            return (Criteria) this;
        }

        public Criteria andKdDescriptionNotLike(String value) {
            addCriterion("kd_description not like", value, "kdDescription");
            return (Criteria) this;
        }

        public Criteria andKdDescriptionIn(List<String> values) {
            addCriterion("kd_description in", values, "kdDescription");
            return (Criteria) this;
        }

        public Criteria andKdDescriptionNotIn(List<String> values) {
            addCriterion("kd_description not in", values, "kdDescription");
            return (Criteria) this;
        }

        public Criteria andKdDescriptionBetween(String value1, String value2) {
            addCriterion("kd_description between", value1, value2, "kdDescription");
            return (Criteria) this;
        }

        public Criteria andKdDescriptionNotBetween(String value1, String value2) {
            addCriterion("kd_description not between", value1, value2, "kdDescription");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(Long value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(Long value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(Long value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(Long value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(Long value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(Long value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<Long> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<Long> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(Long value1, Long value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(Long value1, Long value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPickupCodeIsNull() {
            addCriterion("pickup_code is null");
            return (Criteria) this;
        }

        public Criteria andPickupCodeIsNotNull() {
            addCriterion("pickup_code is not null");
            return (Criteria) this;
        }

        public Criteria andPickupCodeEqualTo(String value) {
            addCriterion("pickup_code =", value, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeNotEqualTo(String value) {
            addCriterion("pickup_code <>", value, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeGreaterThan(String value) {
            addCriterion("pickup_code >", value, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeGreaterThanOrEqualTo(String value) {
            addCriterion("pickup_code >=", value, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeLessThan(String value) {
            addCriterion("pickup_code <", value, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeLessThanOrEqualTo(String value) {
            addCriterion("pickup_code <=", value, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeLike(String value) {
            addCriterion("pickup_code like", value, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeNotLike(String value) {
            addCriterion("pickup_code not like", value, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeIn(List<String> values) {
            addCriterion("pickup_code in", values, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeNotIn(List<String> values) {
            addCriterion("pickup_code not in", values, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeBetween(String value1, String value2) {
            addCriterion("pickup_code between", value1, value2, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeNotBetween(String value1, String value2) {
            addCriterion("pickup_code not between", value1, value2, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andDestinationIsNull() {
            addCriterion("destination is null");
            return (Criteria) this;
        }

        public Criteria andDestinationIsNotNull() {
            addCriterion("destination is not null");
            return (Criteria) this;
        }

        public Criteria andDestinationEqualTo(String value) {
            addCriterion("destination =", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationNotEqualTo(String value) {
            addCriterion("destination <>", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationGreaterThan(String value) {
            addCriterion("destination >", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationGreaterThanOrEqualTo(String value) {
            addCriterion("destination >=", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationLessThan(String value) {
            addCriterion("destination <", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationLessThanOrEqualTo(String value) {
            addCriterion("destination <=", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationLike(String value) {
            addCriterion("destination like", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationNotLike(String value) {
            addCriterion("destination not like", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationIn(List<String> values) {
            addCriterion("destination in", values, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationNotIn(List<String> values) {
            addCriterion("destination not in", values, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationBetween(String value1, String value2) {
            addCriterion("destination between", value1, value2, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationNotBetween(String value1, String value2) {
            addCriterion("destination not between", value1, value2, "destination");
            return (Criteria) this;
        }

        public Criteria andSpecialAttentionIsNull() {
            addCriterion("special_attention is null");
            return (Criteria) this;
        }

        public Criteria andSpecialAttentionIsNotNull() {
            addCriterion("special_attention is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialAttentionEqualTo(String value) {
            addCriterion("special_attention =", value, "specialAttention");
            return (Criteria) this;
        }

        public Criteria andSpecialAttentionNotEqualTo(String value) {
            addCriterion("special_attention <>", value, "specialAttention");
            return (Criteria) this;
        }

        public Criteria andSpecialAttentionGreaterThan(String value) {
            addCriterion("special_attention >", value, "specialAttention");
            return (Criteria) this;
        }

        public Criteria andSpecialAttentionGreaterThanOrEqualTo(String value) {
            addCriterion("special_attention >=", value, "specialAttention");
            return (Criteria) this;
        }

        public Criteria andSpecialAttentionLessThan(String value) {
            addCriterion("special_attention <", value, "specialAttention");
            return (Criteria) this;
        }

        public Criteria andSpecialAttentionLessThanOrEqualTo(String value) {
            addCriterion("special_attention <=", value, "specialAttention");
            return (Criteria) this;
        }

        public Criteria andSpecialAttentionLike(String value) {
            addCriterion("special_attention like", value, "specialAttention");
            return (Criteria) this;
        }

        public Criteria andSpecialAttentionNotLike(String value) {
            addCriterion("special_attention not like", value, "specialAttention");
            return (Criteria) this;
        }

        public Criteria andSpecialAttentionIn(List<String> values) {
            addCriterion("special_attention in", values, "specialAttention");
            return (Criteria) this;
        }

        public Criteria andSpecialAttentionNotIn(List<String> values) {
            addCriterion("special_attention not in", values, "specialAttention");
            return (Criteria) this;
        }

        public Criteria andSpecialAttentionBetween(String value1, String value2) {
            addCriterion("special_attention between", value1, value2, "specialAttention");
            return (Criteria) this;
        }

        public Criteria andSpecialAttentionNotBetween(String value1, String value2) {
            addCriterion("special_attention not between", value1, value2, "specialAttention");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineDateIsNull() {
            addCriterion("order_deadline_date is null");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineDateIsNotNull() {
            addCriterion("order_deadline_date is not null");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineDateEqualTo(Date value) {
            addCriterionForJDBCDate("order_deadline_date =", value, "orderDeadlineDate");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("order_deadline_date <>", value, "orderDeadlineDate");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineDateGreaterThan(Date value) {
            addCriterionForJDBCDate("order_deadline_date >", value, "orderDeadlineDate");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("order_deadline_date >=", value, "orderDeadlineDate");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineDateLessThan(Date value) {
            addCriterionForJDBCDate("order_deadline_date <", value, "orderDeadlineDate");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("order_deadline_date <=", value, "orderDeadlineDate");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineDateIn(List<Date> values) {
            addCriterionForJDBCDate("order_deadline_date in", values, "orderDeadlineDate");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("order_deadline_date not in", values, "orderDeadlineDate");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("order_deadline_date between", value1, value2, "orderDeadlineDate");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("order_deadline_date not between", value1, value2, "orderDeadlineDate");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineTimeIsNull() {
            addCriterion("order_deadline_time is null");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineTimeIsNotNull() {
            addCriterion("order_deadline_time is not null");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineTimeEqualTo(Date value) {
            addCriterionForJDBCTime("order_deadline_time =", value, "orderDeadlineTime");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineTimeNotEqualTo(Date value) {
            addCriterionForJDBCTime("order_deadline_time <>", value, "orderDeadlineTime");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineTimeGreaterThan(Date value) {
            addCriterionForJDBCTime("order_deadline_time >", value, "orderDeadlineTime");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("order_deadline_time >=", value, "orderDeadlineTime");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineTimeLessThan(Date value) {
            addCriterionForJDBCTime("order_deadline_time <", value, "orderDeadlineTime");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("order_deadline_time <=", value, "orderDeadlineTime");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineTimeIn(List<Date> values) {
            addCriterionForJDBCTime("order_deadline_time in", values, "orderDeadlineTime");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineTimeNotIn(List<Date> values) {
            addCriterionForJDBCTime("order_deadline_time not in", values, "orderDeadlineTime");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("order_deadline_time between", value1, value2, "orderDeadlineTime");
            return (Criteria) this;
        }

        public Criteria andOrderDeadlineTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("order_deadline_time not between", value1, value2, "orderDeadlineTime");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDelIsNull() {
            addCriterion("del is null");
            return (Criteria) this;
        }

        public Criteria andDelIsNotNull() {
            addCriterion("del is not null");
            return (Criteria) this;
        }

        public Criteria andDelEqualTo(Boolean value) {
            addCriterion("del =", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelNotEqualTo(Boolean value) {
            addCriterion("del <>", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelGreaterThan(Boolean value) {
            addCriterion("del >", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelGreaterThanOrEqualTo(Boolean value) {
            addCriterion("del >=", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelLessThan(Boolean value) {
            addCriterion("del <", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelLessThanOrEqualTo(Boolean value) {
            addCriterion("del <=", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelIn(List<Boolean> values) {
            addCriterion("del in", values, "del");
            return (Criteria) this;
        }

        public Criteria andDelNotIn(List<Boolean> values) {
            addCriterion("del not in", values, "del");
            return (Criteria) this;
        }

        public Criteria andDelBetween(Boolean value1, Boolean value2) {
            addCriterion("del between", value1, value2, "del");
            return (Criteria) this;
        }

        public Criteria andDelNotBetween(Boolean value1, Boolean value2) {
            addCriterion("del not between", value1, value2, "del");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}