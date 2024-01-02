<template>   
    <div v-if="note" class="edit-form">
        <h2>EditNote</h2>
        <div style="font-size: 15px; color:rgb(6, 10, 243)">
            Updated time: {{ note.updatedtime }}
        </div>
        <br>

        <form>
            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" class="form-control" id="title"
                v-model="note.title"
                />
            </div>
            <div class="form-group">
                <label for="content">Description</label>
                <textarea style="height: 300px" type="text" class="form-control" id="content"
                v-model="note.content"
                ></textarea>
            </div>
        </form>
        <br>
        <button type="submit" class="btn btn-primary"
        @click="updateNote">
            Update
        </button>
        <br>
        <p style="font-size: 15px; margin-top: 10px; color:rgba(229, 33, 33, 0.884)">{{ message }}</p>
    </div>
</template>

<script>
    import NoteDataService from '../services/NoteDataService';

    export default{
        name: 'NoteDetail',
        data() {
            return {
              note: null,
              message: ''
            }
        },

        methods: {
          getNotebyId(id){  
            NoteDataService.get(id).then((response) => {
                this.note = response.data;  
            })
          },

          updateNote() {
            if(this.note.title && this.note.content) {
                NoteDataService.update(this.note.id, this.note).then(() => {
                    this.message = 'The Note was updated successfully!';
                })
            } else {
                alert("All fields must have a valid value");
            }
          }
        },

        mounted() {
            this.getNotebyId(this.$route.params.id);
        }

    }
</script>

<style scoped>
    .edit-form {
    max-width: 800px;
    margin: auto;
    }
</style>