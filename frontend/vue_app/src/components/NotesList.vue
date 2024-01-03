<template>
    <div style="margin-left: 2px">
      <router-link :to="'/notetaking/'" style="font-size:30px; margin-top: 30px; margin-left:270px; color:rgba(37, 37, 186, 0.988)">
        My Notes
      </router-link>

      <!-- Delete Notes Form  -->
      <p class="select-parent">
        Delete:
        <select class="select-child" v-model="deletebox">
          <option value="delete-selected" id="delete-selected">Delete selected box</option>
          <option value="delete-all" id="delete-all">Delete all</option>
        </select>
        <button class="select-child" id="deletebtn" @click="deleteNotes()" style="font-size:13px">
          Go
        </button>
      </p>
      
      <!-- Add Notes Form  -->
      <div class="submit-form" id="addNote" style="margin-left: 270px; width: 940px">
        <button class="btn btn-primary btn-xs" @click="saveNote()">
          Add-Note
        </button>
        <br>
        <label class="form-label" for="addtitle" style="font-size: 15px;">Title</label>
        <p>
          <input class="form-control" id="title" name="title" type="text" required v-model="newNote.title"/>
          <span v-if="v$.newNote.title.$error">
            {{ v$.newNote.title.$errors[0].$message }}
          </span>
        </p>
        <label  class="form-label" for="addcontent" style="font-size: 15px;">Content</label>
        <p>
          <textarea class="form-control" id="content" name="content" type="text" style="height:100px" 
          required v-model="newNote.content"></textarea>
          <span v-if="v$.newNote.content.$error">
            {{ v$.newNote.content.$errors[0].$message }}
          </span>
        </p>
      </div>

      <br>

      <!-- Display container here -->
      <div class="grid-container" id="container">
          <template v-for="note in notes" v-bind:key="note.id">
              <div class="col-lg-4">
                  <div class="panel panel-primary">
                      <div class="panel-heading">

                          <div class="panel-heading-element">
                              {{ note.title }}
                          </div>

                          <router-link :to=" '/notetaking/' + note.id " target="_blank" class="panel-heading-element" 
                           style="color:rgba(223, 241, 84, 0.988)">
                            Edit
                          </router-link>

                          <div class="panel-heading-element" style="padding-right: 10px;">
                              <input type="checkbox" id="check" name="noteid"  @click="check(note.id)"/>
                          </div>

                      </div>

                      <div class="panel-body">
                          <div style="white-space: pre-wrap">
                            {{ note.content }}
                          </div>
                      </div>

                  </div>
              </div>
          </template>
      </div>
      <div style="clear:both;"></div>
    </div>

    <paginate
    v-model="currentPage"
    :page-count="totalPages"
    :page-range="3"
    :margin-pages="1"
    :click-handler="clickCallback"
    :prev-text="'Prev'"
    :next-text="'Next'"
    :container-class="'pagination'"
    :page-class="'page-item'"
    >
    </paginate>
</template>


<script>
    import NoteDataService from '../services/NoteDataService';
    import useValidate from '@vuelidate/core';
    import { required } from '@vuelidate/validators';
    import Paginate from 'vuejs-paginate-next';

    export default{
        components: {
          paginate: Paginate,
        },
        name: 'NoteItems',
        data() {
            return {
              notes: [],
              
              currentPage: 1,
              totalPages: 0,
              pageSize: 12,

              v$: useValidate(),
              newNote: {
                title: "",
                content: "",
              },
              checkedNotes: [],
              deletebox: null
            }
        },

        methods: {
          getRequestParams(currentPage, pageSize) {
            let params = {};

            let page = currentPage - 1;

            if (currentPage >= 0) {
              params["page"] = page;
            }

            if (pageSize) {
              params["size"] = pageSize;
            }

            return params;
          },

          getAllNotes(){
            const params = this.getRequestParams(
              this.currentPage,
              this.pageSize
            );


            NoteDataService.getAll(params).then((response) => {
              const { notes, totalPages} = response.data;
              this.notes = notes;
              this.totalPages = totalPages;
            })
          },

          clickCallback(pageNum) {
            this.currentPage = pageNum;
            this.getAllNotes();
          },

          saveNote() {
            var data = {
              title: this.newNote.title,
              content: this.newNote.content
            };

            this.v$.$validate()

            if (!this.v$.$error) {
              NoteDataService.create(data).then(() => {
                this.newNote.title = null;
                this.newNote.content = null;
                this.getAllNotes();
              })
            }
          },

          check(noteid) {
            this.checkedNotes.push(noteid);
          },

          deleteNotes() {
            var action = confirm("Are you sure you want to delete these notes?"); 
            if(this.deletebox == "delete-selected") {
              if (this.checkedNotes.length != 0) {
                if (action != false) {
                  NoteDataService.delete(this.checkedNotes).then(() => {
                      this.getAllNotes();
                      this.checkedNotes = [];
                  })
                }
              } else {
                alert("Please check the box of notes you want to remove!"); 
              }
            }

            if(this.deletebox == "delete-all") {
              if (action != false) {
                NoteDataService.deleteAll().then(() => {
                    this.getAllNotes();
                })
              }
            }
          }
        },

        created() {
            this.getAllNotes();
        },

        validations() {
          return {
            newNote: {
                title: { required },
                content: { required },
              },
          }
        },

    }
</script>

<style scoped>
    .grid-container {
      display: flex;
      flex-direction: row;
      flex-wrap: wrap;
      justify-content: flex-start;
      width: 960px;
      margin-left: 255px
    }

    .pagination {
      margin-left: 550px; 
    }

    .panel-heading {
      display: flex;
      flex-wrap: wrap;
      column-gap: 200px;
    }
    
    .panel-heading-element {
      padding-left: 0px;
    }
    
    .select-parent {
      display: flex;
      margin-left:950px;
    }

    .select-child {
      margin-inline: 2px;
    }
</style>