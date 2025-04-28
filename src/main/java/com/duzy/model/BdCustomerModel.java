package com.duzy.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 业户表，业主租户
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-17
 */

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bd_customer")
public class BdCustomerModel extends CustomerModel {


    @Schema(description = "租户ID")
    @TableField("tenant_id")
    private Long tenantId;

    @Schema(description = "项目ID")
    @TableField("project_id")
    private Long projectId;

    @Schema(description = "所属组织机构ID")
    @TableField("org_id")
    private Long orgId;

    @Schema(description = "客商类型(业户/开发商/供应商)")
    @TableField("type")
    private String type;

    @Schema(description = "业户类型")
    @TableField("customer_type")
    private String customerType;

    @Schema(description = "客户名称")
    @TableField("name")
    private String name;

    @Schema(description = "手机号码区域/类型")
    @TableField("phone_type")
    private String phoneType;

    @Schema(description = "手机号码")
    @TableField("phone")
    private String phone;

    @Schema(description = "联系电话")
    @TableField("telephone")
    private String telephone;

    @Schema(description = "电子邮箱")
    @TableField("email")
    private String email;

    @Schema(description = "性别")
    @TableField("sex")
    private Boolean sex;

    @Schema(description = "民族")
    @TableField("nation")
    private String nation;

    @Schema(description = "证件类型ID")
    @TableField("certificate_type_id")
    private Long certificateTypeId;

    @Schema(description = "证件类型")
    @TableField("certificate_type")
    private String certificateType;

    @Schema(description = "证件号码")
    @TableField("certificateno")
    private String certificateno;

    @Schema(description = "出生日期")
    @TableField("birthday")
    private LocalDate birthday;

    @Schema(description = "国籍")
    @TableField("country")
    private String country;

    @Schema(description = "联系地址")
    @TableField("address")
    private String address;

    @Schema(description = "客户等级ID")
    @TableField("customer_grade_id")
    private Long customerGradeId;

    @Schema(description = "客户等级")
    @TableField("customer_grade")
    private String customerGrade;

    @Schema(description = "行业分类ID")
    @TableField("industry_category_id")
    private Long industryCategoryId;

    @Schema(description = "行业分类")
    @TableField("industry_category")
    private String industryCategory;

    @Schema(description = "纳税人识别号")
    @TableField("tax_id")
    private String taxId;

    @Schema(description = "公司联系人姓名")
    @TableField("contact_name")
    private String contactName;

    @Schema(description = "待删除")
    @TableField("deposit_bank")
    private String depositBank;

    @Schema(description = "公司账号")
    @TableField("company_account")
    private String companyAccount;

    @Schema(description = "邮政编号")
    @TableField("postal_code")
    private String postalCode;

    @Schema(description = "行业分类id")
    @TableField("job_type_Id")
    private Long jobTypeId;

    @Schema(description = "职务")
    @TableField("post")
    private String post;

    @Schema(description = "行业分类")
    @TableField("job_type_name")
    private String jobTypeName;

    @Schema(description = "是否大陆企业")
    @TableField("china_customer")
    private Boolean chinaCustomer;

    @Schema(description = "是否内部单位")
    @TableField("unit_internal")
    private Boolean unitInternal;

    @Schema(description = "所属公司名称")
    @TableField("belong_company")
    private String belongCompany;

    @Schema(description = "封存")
    @TableField("sealed")
    private Boolean sealed;

    @Schema(description = "品类id（字典值）")
    @TableField("class_id")
    private Long classId;

    @Schema(description = "品类")
    @TableField("class_name")
    private String className;

    @Schema(description = "是否提报业绩")
    @TableField("performance_enable")
    private Boolean performanceEnable;

    @Schema(description = "待删除")
    @TableField("bank_acount")
    private String bankAcount;

    @Schema(description = "发票电话")
    @TableField("invoice_tel")
    private String invoiceTel;

    @Schema(description = "发票地址")
    @TableField("invoice_address")
    private String invoiceAddress;

    @Schema(description = "编号")
    @TableField("code")
    private String code;

    @Schema(description = "发票名称(收款方户名)")
    @TableField("invoice_name")
    private String invoiceName;

    @Schema(description = "发票开户行(开户行名称)")
    @TableField("invoice_account_bank")
    private String invoiceAccountBank;

    @Schema(description = "发票开户行账号(收款银行账号)")
    @TableField("invoice_account")
    private String invoiceAccount;

    @Schema(description = "户籍所在地")
    @TableField("regist_address")
    private String registAddress;

    @Schema(description = "微信unionId")
    @TableField("union_id")
    private String unionId;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @Schema(description = "创建人id")
    @TableField("create_id")
    private Long createId;

    @Schema(description = "创建人姓名")
    @TableField("create_name")
    private String createName;

    @Schema(description = "修改时间")
    @TableField("modifier_time")
    private LocalDateTime modifierTime;

    @Schema(description = "修改人id")
    @TableField("modifier_id")
    private Long modifierId;

    @Schema(description = "修改人姓名")
    @TableField("modifier_name")
    private String modifierName;

    @Schema(description = "招商经理id")
    @TableField("lease_manager_id")
    private Long leaseManagerId;

    @Schema(description = "招商经理名称")
    @TableField("lease_manager_name")
    private String leaseManagerName;

    @Schema(description = "外部来源")
    @TableField("extsource")
    private String extsource;

    @Schema(description = "外部ID")
    @TableField("ext_id")
    private String extId;

    @Schema(description = "外部code")
    @TableField("ext_code")
    private String extCode;

    @Schema(description = "外部备注")
    @TableField("ext_memo")
    private String extMemo;

    @Schema(description = "是否来源于集团客商")
    @TableField("from_org_customer")
    private Boolean fromOrgCustomer;

    @Schema(description = "集团客商id")
    @TableField("org_customer_id")
    private Long orgCustomerId;

    @Schema(description = "发票手机号")
    @TableField("invoice_phone")
    private String invoicePhone;

    @Schema(description = "发票邮箱")
    @TableField("invoice_email")
    private String invoiceEmail;

    @Schema(description = "优质客户")
    @TableField("good_customer")
    private Boolean goodCustomer;

    @Schema(description = "预付款监管")
    @TableField("advance_payment_supervision")
    private Boolean advancePaymentSupervision;

    @Schema(description = "备注")
    @TableField("remark")
    private String remark;

    @Schema(description = "企业性质id")
    @TableField("enterprise_properties_id")
    private Long enterprisePropertiesId;

    @Schema(description = "企业性质名称")
    @TableField("enterprise_properties_name")
    private String enterprisePropertiesName;

    @Schema(description = "年纳税金额(万元)")
    @TableField("year_tax_amount")
    private BigDecimal yearTaxAmount;

    @Schema(description = "信用度")
    @TableField("credit_degree")
    private String creditDegree;

    @Schema(description = "信用度Id")
    @TableField("credit_degree_id")
    private Long creditDegreeId;

    @Schema(description = "欠费类别")
    @TableField("arrears_category")
    private String arrearsCategory;

    @Schema(description = "欠费类别Id")
    @TableField("arrears_category_id")
    private Long arrearsCategoryId;

    @Schema(description = "兴趣爱好")
    @TableField("hobby")
    private String hobby;

    @Schema(description = "附加说明")
    @TableField("additional_remark")
    private String additionalRemark;

    @Schema(description = "职业id")
    @TableField("profession_id")
    private Long professionId;

    @Schema(description = "职业")
    @TableField("profession")
    private String profession;

    @Schema(description = "工作单位")
    @TableField("work_unit")
    private String workUnit;

    @Schema(description = "是否内部客户")
    @TableField("if_internal")
    private Boolean ifInternal;

    @Schema(description = "人员办公数量")
    @TableField("total_staff")
    private Integer totalStaff;

    @Schema(description = "公司简称")
    @TableField("for_short")
    private String forShort;

    @Schema(description = "企业联系人")
    @TableField("enterprise_contact")
    private String enterpriseContact;

    @Schema(description = "企业联系人证件号码")
    @TableField("contact_certificateno")
    private String contactCertificateno;

    @Schema(description = "联系人电话")
    @TableField("contact_phone")
    private String contactPhone;
}
