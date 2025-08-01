<template>
  <div class="tag-list">
    <el-tag
      v-for="tag in tags"
      :key="tag"
      type="info"
      closable
      @close="removeTag(tag)"
      class="tag-item"
    >
      {{ tag }}
    </el-tag>
    <el-input
      v-if="editing"
      v-model="newTag"
      placeholder="Add a tag"
      size="small"
      @keyup.enter="addTag"
      class="tag-input"
    />
    <el-button
      v-else
      icon="el-icon-plus"
      size="small"
      @click="editing = true"
      class="add-button"
    ></el-button>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const props = defineProps({
  tags: {
    type: Array,
    default: () => []
  }
})
const emits = defineEmits(['update:tags'])

const editing = ref(false)
const newTag = ref('')

const addTag = () => {
  const trimmed = newTag.value.trim()
  if (trimmed && !props.tags.includes(trimmed)) {
    emits('update:tags', [...props.tags, trimmed])
  }
  newTag.value = ''
  editing.value = false
}

const removeTag = (tag) => {
  const filtered = props.tags.filter(t => t !== tag)
  emits('update:tags', filtered)
}
</script>

<style scoped>
.tag-list {
  display: flex;
  flex-wrap: wrap;
  gap: 6px;
  align-items: center;
}
.tag-item {
  margin-right: 5px;
}
.tag-input {
  width: 100px;
}
</style>
