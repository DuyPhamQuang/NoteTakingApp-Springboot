import http from "../http-common";
import authHeader from './auth-header';

class NoteDataService {
  getAll(params) {
    return http.get("/notetaking/", {params, headers: authHeader() });
  }

  search(params) {
    return http.get("/notetaking/search", {params, headers: authHeader()});
  }

  get(id) {
    return http.get(`/notetaking/${id}`, {headers: authHeader()});
  }

  create(data) {
    return http.post("/notetaking/addNote", data, {headers: authHeader()});
  }

  update(id, data) {
    return http.put(`/notetaking/update/${id}`, data, {headers: authHeader()});
  }

  delete(ids = [], data) {
    return http.post(`/notetaking/delete/${ids}`, data, {headers: authHeader()})
  }

  deleteAll() {
    return http.post(`/notetaking/delete_all`, {headers: authHeader()});
  }
}

export default new NoteDataService();