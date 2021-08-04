<template>

  <a-layout>
    <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
        <a-form
                layout="inline"
                :model="param"
        >
            <a-form-item>
                <a-button type="primary" @click="handleQuery()" >
                    查询
                </a-button>
            </a-form-item>
            <a-form-item>
                <a-button type="primary" @click="add()" >
                    新增
                </a-button>
            </a-form-item>
        </a-form>
      <a-table
              :columns="columns"
              :row-key="record => record.id"
              :data-source="level1"
              :pagination="false"
              :loading="loading"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar" />
        </template>
        <template v-slot:action="{ text, record }">
          <a-space size="small">
            <a-button type="primary" @click="edit(record)">
              编辑
            </a-button>
              <a-popconfirm
                      title="删除后不可恢复，确认删除?"
                      ok-text="是"
                      cancel-text="否"
                      @confirm="handleDelete(record.id)"
              >
                  <a-button type="danger" >
                      删除
                  </a-button>
              </a-popconfirm>
          </a-space>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>
    <!--新增 编辑对话框-->
    <a-modal
            title="文档表单"
            v-model:visible="modalVisible"
            :confirm-loading="modalLoading"
            @ok="handleModalOk"
    >
        <!--表单-->
        <a-form :model="doc" :label-col="{span : 6}" :wrapper-col="{ span: 18 }">
            <a-form-item label="名称">
                <a-input v-model:value="doc.name" />
            </a-form-item>
            <a-form-item label="父文档">
                <a-select
                        v-model:value="doc.parent"
                        ref="select"
                >
                    <a-select-option :value="0">无</a-select-option>
                    <a-select-option v-for="c in level1" :key="c.id" :value="c.id" :disabled="doc.id === c.id">
                        {{c.name}}
                    </a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="排序">
                <a-input v-model:value="doc.sort" />
            </a-form-item>
        </a-form>
    </a-modal>

</template>

<script lang="ts">
  import { defineComponent,onMounted ,ref} from 'vue';
  import axios from 'axios';
  import {message} from 'ant-design-vue'
  import {Tool} from "../../util/tool";

  export default defineComponent({
    name: 'AdminDoc',
    setup() {
      const param = ref();
      param.value = {};
      const docs = ref();
      const loading = ref(false);

      const columns = [
        {
          title: '名称',
          dataIndex: 'name'
        },
        {
          title: '父文档',
          key:'parent',
          dataIndex:'parent',
        },
        {
          title: '排序',
          key:'sort',
          dataIndex:'sort',
        },
        {
          title: 'Action',
          key: 'action',
          slots: { customRender: 'action' }
        }
      ];

        /**
         * 一级文档树，children属性就是二级文档
         * [{
         *   id: "",
         *   name: "",
         *   children: [{
         *     id: "",
         *     name: "",
         *   }]
         * }]
         */
        const level1 = ref(); // 一级文档树，children属性就是二级文档
        level1.value = [];

      /**
       * 刚刚加载就进入数据查询，在onMounted中使用
       **/
      const handleQuery = () => {
        loading.value = true;
          // 如果不清空现有数据，则编辑保存重新加载数据后，再点编辑，则列表显示的还是编辑前的数据
        docs.value = [];
        axios.get("/doc/all",).then((response) => {
          loading.value = false;
          const data = response.data;
          //返回success才执行，否则返回错误信息
          if(data.success){
              docs.value = data.content;
              console.log("原始数组：", docs.value);

              level1.value = [];
              level1.value = Tool.array2Tree(docs.value,0);
          }else {
              message.error(data.message);
          }
        });
      };

      //表单
        const modalVisible = ref(false);
        const modalLoading = ref(false);
        const doc = ref({});
        //点击保存
        const handleModalOk = () => {
            modalLoading.value = true;
            axios.post("/doc/save", doc.value).then((response) => {
                modalLoading.value = false;
                const data = response.data;  //data = CommResp
                if(data.success){
                    modalVisible.value = false;
                    //重新加载列表
                    handleQuery();
                }else {
                    message.error(data.message);
                }
            });
        };
        //编辑
        const edit = (record : any) => {
            modalVisible.value = true;
            doc.value = Tool.copy(record);
        };
        /**
         * 新增
         */
        const add = () => {
            modalVisible.value = true;
            doc.value = {};
        };

        /**
         * 删除
         */
        const handleDelete = (id : number) => {
            axios.delete("/doc/delete/" + id).then((response) => {
                const data = response.data;  //data = CommResp
                if(data.success){
                    //重新加载列表
                    handleQuery();
                }
            });
        };

        //进入程序及执行
        onMounted(() =>{
        handleQuery();
      });

      return {
        docs,
        columns,
        loading,

        edit,
        add,

        doc,
        modalVisible,
        modalLoading,
        handleModalOk,
        handleDelete,
        handleQuery,
        param,
        level1
      };

    }
  });

</script>

<style scoped>
  img {
    width: 50px;
    height: 50px;
  }
</style>
