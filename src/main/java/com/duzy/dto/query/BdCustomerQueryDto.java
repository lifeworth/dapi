package com.duzy.dto.query;

import io.swagger.v3.oas.annotations.media.Schema;
import com.duzy.dto.BaseQueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@Schema(name = "BdCustomerQueryDto")
public class BdCustomerQueryDto extends BaseQueryDTO{

    @Schema(description = "租户ID")
    private Long tenantId;
    @Schema(description = "项目ID")
    private Long projectId;
    @Schema(description = "所属组织机构ID")
    private Long orgId;
    @Schema(description = "客商类型(业户/开发商/供应商)")
    private String type;
    @Schema(description = "业户类型")
    private String customerType;
    @Schema(description = "客户名称")
    private String name;
    @Schema(description = "手机号码区域/类型")
    private String phoneType;
    @Schema(description = "手机号码")
    private String phone;
    @Schema(description = "联系电话")
    private String telephone;
    @Schema(description = "电子邮箱")
    private String email;
    @Schema(description = "性别")
    private Boolean sex;
    @Schema(description = "民族")
    private String nation;
    @Schema(description = "证件类型ID")
    private Long certificateTypeId;
    @Schema(description = "证件类型")
    private String certificateType;
    @Schema(description = "证件号码")
    private String certificateno;
    @Schema(description = "出生日期")
    private LocalDate birthday;
    @Schema(description = "国籍")
    private String country;
    @Schema(description = "联系地址")
    private String address;
    @Schema(description = "客户等级ID")
    private Long customerGradeId;
    @Schema(description = "客户等级")
    private String customerGrade;
    @Schema(description = "行业分类ID")
    private Long industryCategoryId;
    @Schema(description = "行业分类")
    private String industryCategory;
    @Schema(description = "纳税人识别号")
    private String taxId;
    @Schema(description = "公司联系人姓名")
    private String contactName;
    @Schema(description = "待删除")
    private String depositBank;
    @Schema(description = "公司账号")
    private String companyAccount;
    @Schema(description = "邮政编号")
    private String postalCode;
    @Schema(description = "行业分类id")
    private Long jobTypeId;
    @Schema(description = "职务")
    private String post;
    @Schema(description = "行业分类")
    private String jobTypeName;
    @Schema(description = "是否大陆企业")
    private Boolean chinaCustomer;
    @Schema(description = "是否内部单位")
    private Boolean unitInternal;
    @Schema(description = "所属公司名称")
    private String belongCompany;
    @Schema(description = "封存")
    private Boolean sealed;
    @Schema(description = "品类id（字典值）")
    private Long classId;
    @Schema(description = "品类")
    private String className;
    @Schema(description = "是否提报业绩")
    private Boolean performanceEnable;
    @Schema(description = "待删除")
    private String bankAcount;
    @Schema(description = "发票电话")
    private String invoiceTel;
    @Schema(description = "发票地址")
    private String invoiceAddress;
    @Schema(description = "编号")
    private String code;
    @Schema(description = "发票名称(收款方户名)")
    private String invoiceName;
    @Schema(description = "发票开户行(开户行名称)")
    private String invoiceAccountBank;
    @Schema(description = "发票开户行账号(收款银行账号)")
    private String invoiceAccount;
    @Schema(description = "户籍所在地")
    private String registAddress;
    @Schema(description = "微信unionId")
    private String unionId;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @Schema(description = "创建人id")
    private Long createId;
    @Schema(description = "创建人姓名")
    private String createName;
    @Schema(description = "修改时间")
    private LocalDateTime modifierTime;
    @Schema(description = "修改人id")
    private Long modifierId;
    @Schema(description = "修改人姓名")
    private String modifierName;
    @Schema(description = "招商经理id")
    private Long leaseManagerId;
    @Schema(description = "招商经理名称")
    private String leaseManagerName;
    @Schema(description = "外部来源")
    private String extsource;
    @Schema(description = "外部ID")
    private String extId;
    @Schema(description = "外部code")
    private String extCode;
    @Schema(description = "外部备注")
    private String extMemo;
    @Schema(description = "是否来源于集团客商")
    private Boolean fromOrgCustomer;
    @Schema(description = "集团客商id")
    private Long orgCustomerId;
    @Schema(description = "发票手机号")
    private String invoicePhone;
    @Schema(description = "发票邮箱")
    private String invoiceEmail;
    @Schema(description = "优质客户")
    private Boolean goodCustomer;
    @Schema(description = "预付款监管")
    private Boolean advancePaymentSupervision;
    @Schema(description = "备注")
    private String remark;
    @Schema(description = "企业性质id")
    private Long enterprisePropertiesId;
    @Schema(description = "企业性质名称")
    private String enterprisePropertiesName;
    @Schema(description = "年纳税金额(万元)")
    private BigDecimal yearTaxAmount;
    @Schema(description = "信用度")
    private String creditDegree;
    @Schema(description = "信用度Id")
    private Long creditDegreeId;
    @Schema(description = "欠费类别")
    private String arrearsCategory;
    @Schema(description = "欠费类别Id")
    private Long arrearsCategoryId;
    @Schema(description = "兴趣爱好")
    private String hobby;
    @Schema(description = "附加说明")
    private String additionalRemark;
    @Schema(description = "职业id")
    private Long professionId;
    @Schema(description = "职业")
    private String profession;
    @Schema(description = "工作单位")
    private String workUnit;
    @Schema(description = "是否内部客户")
    private Boolean ifInternal;
    @Schema(description = "人员办公数量")
    private Integer totalStaff;
    @Schema(description = "公司简称")
    private String forShort;
    @Schema(description = "企业联系人")
    private String enterpriseContact;
    @Schema(description = "企业联系人证件号码")
    private String contactCertificateno;
    @Schema(description = "联系人电话")
    private String contactPhone;
}
