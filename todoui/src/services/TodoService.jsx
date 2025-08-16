import axios from "axios";
const API_URL = '/api/todos';

// export  function getAllTodos() {
//     return axios.get(API_URL);
export const getAll = () =>  axios.get(API_URL);
export const saveTodo = (todo) => axios.post("/api/todos/add", todo);
export const getTodo = (id) => axios.get("/api/todos" + "/"+id);
export const updateTodo = (id, todo) => axios.put("/api/todos/update" + "/"+id, todo);
export const deleteTodo = (id) => axios.delete("/api/todos/delete" + "/"+id);
export const completeTodo = (id) => axios.patch("/api/todos/complete" + "/"+id);
export const inCompleteTodo = (id) => axios.patch("/api/todos/incomplete" + "/"+id);


