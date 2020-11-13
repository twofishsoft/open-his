<template>
  <div class="app-container">
    <el-form ref="queryForm" :model="queryParams" :inline="true">
      <el-form-item label="角色名称" prop="roleName">
        <el-input
          v-model="queryParams.roleName"
          placeholder="请输入角色名称"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="权限字符" prop="roleCode">
        <el-input
          v-model="queryParams.roleCode"
          placeholder="请输入权限字符"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="角色状态"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="dateRange"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-thumb"
          size="mini"
          :disabled="single"
          @click="handleMenuData"
        >分配权限</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="roleList" border @selection-change="handleSelectionChange">
      <el-table-column type="selection" align="center" width="70" />
      <el-table-column label="角色编号" prop="roleId" align="center" width="150" />
      <el-table-column label="角色名称" prop="roleName" align="center" :show-overflow-tooltip="true" />
      <el-table-column label="权限编码" prop="roleCode" align="center" :show-overflow-tooltip="true" />
      <el-table-column label="显示顺序" prop="roleSort" align="center" />
      <el-table-column label="状态" align="center">
        <template v-slot="{row}">
          <el-tag :type="row.status == 0 ? 'success' : 'danger'">{{ row.statusName }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="备注" prop="remark" align="center" />
      <el-table-column label="创建时间" align="center" prop="createTime">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding" width="300">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-thumb"
            @click="handleMenuData(scope.row)"
          >分配权限</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改角色配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="form.roleName" placeholder="请输入角色名称" />
        </el-form-item>
        <el-form-item label="权限编码" prop="roleCode">
          <el-input v-model="form.roleCode" placeholder="请输入权限字符" />
        </el-form-item>
        <el-form-item label="角色顺序">
          <el-input-number v-model="form.roleSort" :step="1" :min="0" size="small" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{ dict.dictLabel }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入角色备注内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 分配角色数据权限对话框 -->
    <el-dialog title="分配权限" :visible.sync="openMenuData" center width="400px" append-to-body :before-close="closeMenu">
      <el-form :model="form" label-width="80px">
        <el-tree
          ref="menu"
          :data="menuOptions"
          show-checkbox
          :default-expanded-keys="[1]"
          node-key="id"
          empty-text="加载中，请稍后"
          :props="defaultProps"
        />
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitDataScope">确 定</el-button>
        <el-button @click="cancelMenuData">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRole, getOne, addRole, updateRole, delRole, updateMenu } from "@/api/system/role"
import { getAllMenu } from "@/api/system/menu"

export default {
  name: "Role",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 角色表格数据
      roleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示弹出层（数据权限）
      openMenuData: false,
      // 日期范围
      dateRange: [],
      // 状态数据字典
      statusOptions: [],
      // 菜单列表
      menuOptions: [],
      // 选中角色
      roleId: undefined,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        roleName: undefined,
        roleCode: undefined,
        status: undefined
      },
      // 表单参数
      form: {},
      defaultProps: {
        children: "children",
        label: "label"
      },
      // 表单校验
      rules: {
        roleName: [
          { required: true, message: "角色名称不能为空", trigger: "blur" }
        ],
        roleCode: [
          { required: true, message: "权限编码不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data
    })
  },
  methods: {
    /** 查询角色列表 */
    getList() {
      this.loading = true
      listRole(this.addDateRange(this.queryParams, this.dateRange)).then(
        response => {
          this.roleList = response.data
          this.total = response.total
          this.loading = false
        }
      )
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 取消按钮（菜单权限）
    cancelMenuData() {
      this.$refs.menu.setCheckedKeys([]);
      this.openMenuData = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        roleId: undefined,
        roleName: undefined,
        roleCode: undefined,
        roleSort: 0,
        status: "0",
        remark: undefined
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = []
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.roleId)
      this.single = selection.length != 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加角色信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const roleId = row.roleId || this.ids
      getOne(roleId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改角色信息"
      })
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.roleId != undefined) {
            updateRole(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功")
                this.open = false
                this.getList()
              }
            })
          } else {
            addRole(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功")
                this.open = false
                this.getList()
              }
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const roleIds = row.roleId || this.ids
      this.$confirm('是否确认删除角色编号为"' + roleIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return delRole(roleIds)
      }).then(() => {
        this.queryParams.pageNum = 1
        this.getList()
        this.msgSuccess("删除成功")
      }).catch(function() {})
    },
    /** 提交按钮（数据权限） */
    submitDataScope: function() {
      const roleId = this.roleId || this.ids[0]
      if (roleId != undefined) {
        const menuIds = this.$refs.menu.getCheckedKeys()
        updateMenu(roleId, menuIds).then(response => {
          if (response.code === 200) {
            this.msgSuccess("修改成功")
            this.openMenuData = false
            this.roleId = undefined
          }
        })
      }
    },
    /** 分配菜单权限操作 */
    handleMenuData(row) {
      this.roleId = row.roleId
      this.openMenuData = true
      this.getMenuTreeSelect()
    },
    /** 查询菜单树结构 和 角色选中的树*/
    getMenuTreeSelect() {
      const roleId = this.roleId || this.ids[0]
      getAllMenu(roleId).then(response => {
        this.menuOptions = response.data
        this.$nextTick(() => {
          this.$refs.menu.setCheckedKeys(response.checkedMenus);
        });
      })
    },
    closeMenu() {
      this.$refs.menu.setCheckedKeys([]);
      this.openMenuData = false
    }
  }
}
</script>
