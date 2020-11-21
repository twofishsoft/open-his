<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--用户数据-->
      <el-col :span="24" :xs="24">
        <el-form ref="queryForm" :model="queryParams" :inline="true">
          <el-form-item label="所属部门" prop="deptId">
            <el-select
              v-model="queryParams.deptId"
              placeholder="请选择所属部门"
              clearable
              filterable
              size="small"
              style="width: 240px"
            >
              <el-option
                v-for="dict in deptOptions"
                :key="dict.deptId"
                :label="dict.deptName"
                :value="dict.deptId"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="用户名称" prop="userName">
            <el-input
              v-model="queryParams.userName"
              placeholder="请输入用户名称"
              clearable
              size="small"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="手机号码" prop="phone">
            <el-input
              v-model="queryParams.phone"
              placeholder="请输入手机号码"
              clearable
              size="small"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select
              v-model="queryParams.status"
              placeholder="可用状态"
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
              :disabled="isAdminM"
              @click="handleDelete"
            >删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="warning"
              icon="el-icon-thumb"
              size="mini"
              :disabled="isAdminS"
              @click="openRoleDialog"
            >分配角色</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              icon="el-icon-refresh"
              size="mini"
              :disabled="multiple"
              @click="handleResetPwd"
            >重置密码</el-button>
          </el-col>
        </el-row>

        <el-table v-loading="loading" :data="userList" border @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="用户ID" align="center" prop="userId" width="100px" />
          <el-table-column label="用户姓名" align="center" prop="userName" :show-overflow-tooltip="true" />
          <el-table-column label="部门" align="center" prop="dept.deptName" />
          <el-table-column label="手机号码" align="center" prop="phone" />
          <el-table-column label="性别" align="center" prop="sex">
            <template v-slot="{row}">
              {{ row.sex | dictDateName(sexOptions) }}
            </template>
          </el-table-column>
          <el-table-column label="年龄" align="center" prop="age" />
          <el-table-column label="是否排班" align="center" prop="schedulingFlagName">
            <template v-slot="{row}">
              <el-tag :type="row.schedulingFlag == 0 ? 'success' : 'danger'">
                {{ row.schedulingFlag | dictDateName(schedulingFlagOptions) }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="级别" align="center" prop="userRank">
            <template v-slot="{row}">
              {{ row.userRank | dictDateName(levelOptions) }}
            </template>
          </el-table-column>
          <el-table-column label="背景" align="center" prop="background">
            <template v-slot="{row}">
              {{ row.background | dictDateName(backgroundOptions) }}
            </template>
          </el-table-column>
          <el-table-column label="状态" align="center">
            <template v-slot="{row}">
              <el-tag :type="row.status == 0 ? 'success' : 'danger'">
                {{ row.status | dictDateName(statusOptions) }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="创建时间" align="center" prop="createTime" width="170">
            <template v-slot="scope">
              <span>{{ parseTime(scope.row.createTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
            align="center"
            width="210"
            class-name="small-padding"
          >
            <template v-slot="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
              >修改</el-button>
              <el-button
                v-if="scope.row.userId !== 1"
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
              >删除</el-button>
              <el-button
                v-if="scope.row.userId !== 1"
                size="mini"
                type="text"
                icon="el-icon-thumb"
                @click="openRoleDialog(scope.row)"
              >分配角色</el-button>
            </template>
          </el-table-column>
        </el-table>
        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />
      </el-col>
    </el-row>

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="11">
            <el-form-item label="姓名" prop="userName">
              <el-input v-model="form.userName" placeholder="请输入用户昵称" />
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="手机号码" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入手机号码" maxlength="11" />
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="所属科室" prop="deptId">
              <el-select v-model="form.deptId" filterable clearable placeholder="请选择">
                <el-option
                  v-for="dict in deptOptions"
                  :key="dict.deptId"
                  :label="dict.deptName"
                  :value="dict.deptId"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="级别" prop="userRank">
              <el-select v-model="form.userRank" placeholder="请选择">
                <el-option
                  v-for="dict in levelOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="年龄">
              <el-input-number v-model="form.age" :step="1" :min="0" size="small" />
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="背景" prop="background">
              <el-select v-model="form.background" placeholder="请选择">
                <el-option
                  v-for="dict in backgroundOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="用户性别">
              <el-radio-group v-model="form.sex">
                <el-radio
                  v-for="dict in sexOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{ dict.dictLabel }}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="状态">
              <el-radio-group v-model="form.status">
                <el-radio
                  v-for="dict in statusOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{ dict.dictLabel }}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="是否参与排班">
              <el-radio-group v-model="form.schedulingFlag">
                <el-radio
                  v-for="dict in schedulingFlagOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{ dict.dictLabel }}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 角色分配 -->
    <el-dialog title="分配角色" :visible.sync="openUpdateRole" center>
      <el-table ref="multipleTable" v-loading="roleLoading" :data="roleList" border @selection-change="handleSelectionRoleChange">
        <el-table-column type="selection" width="50" align="center" />
        <el-table-column label="ID" align="center" prop="roleId" width="100px" />
        <el-table-column label="角色名称" align="center" prop="roleName" />
        <el-table-column label="权限编码" align="center" prop="roleCode" />
        <el-table-column label="备注" align="center" prop="remark" />
        <el-table-column label="创建时间" align="center" prop="createTime" />
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="updateUserRole">确 定</el-button>
        <el-button @click="cancelRole">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listUser, addUser, updateUser, delUser, getOne, resetUserPwd, updateRole } from '@/api/system/user'
import { allDept } from '@/api/system/dept'
import { allRole, getUserRole } from '@/api/system/role'

export default {
  name: 'User',
  data() {
    return {
      // 遮罩层
      loading: true,
      // 角色遮罩层
      roleLoading: true,
      // 多选框选中用户ID数据
      ids: [],
      // 多选框选中选中角色ID数据
      roleIds: [],
      // 分配角色选中的用户
      userId: '',
      // 非单个禁用
      single: true,
      // 是否管理员
      isAdminS: true,
      isAdminM: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 用户表格数据
      userList: [],
      // 角色表格数据
      roleList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 是否显示分配角色
      openUpdateRole: false,
      // 部门名称
      deptName: undefined,
      // 日期范围
      dateRange: [],
      // 部门数据字典
      deptOptions: [],
      // 状态数据字典
      statusOptions: [],
      // 性别状态字典
      sexOptions: [],
      // 级别数据字典
      levelOptions: [],
      // 是否排班数据字典
      schedulingFlagOptions: [],
      // 背景数据字典
      backgroundOptions: [],
      // 表单参数
      form: {},
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: undefined,
        phone: undefined,
        status: undefined,
        deptId: undefined
      },
      // 表单校验
      rules: {
        userName: [
          { required: true, message: '用户名称不能为空', trigger: 'blur' }
        ],
        deptId: [
          { required: true, message: '所属科室不能为空', trigger: 'blur' }
        ],
        userRank: [
          { required: true, message: '级别不能为空', trigger: 'blur' }
        ],
        background: [
          { required: true, message: '背景不能为空', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '手机号码不能为空', trigger: 'blur' },
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: '请输入正确的手机号码',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  beforeCreate() {
    // 状态
    this.getDicts('sys_normal_disable').then(response => {
      this.statusOptions = response.data
    })
    // 性别
    this.getDicts('sys_user_sex').then(response => {
      this.sexOptions = response.data
    })
    // 级别
    this.getDicts('sys_user_level').then(response => {
      console.log(response)
      this.levelOptions = response.data
    })
    // 是否排班
    this.getDicts('his_scheduling_flag').then(response => {
      this.schedulingFlagOptions = response.data
    })
    // 背景
    this.getDicts('sys_user_background').then(response => {
      this.backgroundOptions = response.data
    })
  },
  created() {
    this.getList()
    // 部门
    this.queryAllDept()
  },
  methods: {
    /** 查询用户列表 */
    getList() {
      this.loading = true
      listUser(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.userList = response.data
        this.total = response.total
        this.loading = false
      }
      )
    },
    /** 查询所有的部门数据 */
    queryAllDept() {
      allDept().then(response => {
        this.deptOptions = response.data
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 角色框取消按钮
    cancelRole() {
      this.openUpdateRole = false
    },
    // 表单重置
    reset() {
      this.form = {
        userId: undefined,
        userName: undefined,
        phone: undefined,
        deptId: undefined,
        userRank: undefined,
        age: 0,
        background: undefined,
        sex: undefined,
        status: '0',
        schedulingFlag: undefined
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = []
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.userId)
      this.single = selection.length != 1
      this.multiple = !selection.length
      this.isAdminS = this.single || (this.ids.filter(item => item == '1')).length != 0
      this.isAdminM = this.multiple || (this.ids.filter(item => item == '1')).length != 0
    },
    // 多选框选中角色数据
    handleSelectionRoleChange(selection) {
      this.roleIds = selection.map(item => item.roleId)
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.queryAllDept()
      this.open = true
      this.title = '添加用户'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      this.queryAllDept()
      const userId = row.userId || this.ids
      getOne(userId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改用户'
      })
    },
    /** 重置密码按钮操作 */
    handleResetPwd() {
      this.$prompt('请输入' + this.ids + '的新密码', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
        resetUserPwd(this.ids, value).then(response => {
          if (response.code === 200) {
            this.msgSuccess('修改成功，新密码是：' + value)
          }
        })
      }).catch(() => {})
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.userId != undefined) {
            updateUser(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              }
            })
          } else {
            addUser(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess('新增成功')
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
      const userIds = row.userId || this.ids
      this.$confirm('是否确认删除用户编号为' + userIds + '的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return delUser(userIds)
      }).then(() => {
        this.queryParams.pageNum = 1
        this.getList()
        this.msgSuccess('删除成功')
      }).catch(function() {})
    },
    getUserRole() {
      getUserRole(this.userId).then(response => {
        const data = response.data
        this.roleIds = data.map(item => item.roleId)
        data.forEach(row => {
          const filter = this.roleList.filter(item => item.roleId == row.roleId)
          if (filter.length != 0) {
            this.$refs.multipleTable.toggleRowSelection(filter[0], true)
          }
        })
      })
    },
    openRoleDialog(row) {
      this.userId = row && row.userId
      allRole().then(response => {
        this.roleList = response.data
        this.roleLoading = false
      }).then(response => {
        if (!this.userId) {
          this.userId = this.ids[0]
        }
        this.getUserRole()
        this.openUpdateRole = true
      })
    },
    updateUserRole() {
      this.userId = this.userId || this.ids
      updateRole(this.userId, this.roleIds).then(response => {
        if (response.code == 200) {
          this.msgSuccess('分配角色成功')
          this.openUpdateRole = false
          this.userId = ''
        }
      })
    }
  }
}
</script>
