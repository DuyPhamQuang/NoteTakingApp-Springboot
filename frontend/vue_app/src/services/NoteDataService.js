import http from "../http-common";

class NoteDataService {
  getAll(params) {
    return http.get("/notetaking/", { params });
  }

  search(params) {
    return http.get("/notetaking/search", { params });
  }

  get(id) {
    return http.get(`/notetaking/${id}`);
  }

  create(data) {
    return http.post("/notetaking/addNote", data);
  }

  update(id, data) {
    return http.put(`/notetaking/update/${id}`, data);
  }

  delete(ids = []) {
    return http.put(`/notetaking/delete/${ids}`);
  }

  deleteAll() {
    return http.put(`/notetaking/delete_all`);
  }
}

export default new NoteDataService();