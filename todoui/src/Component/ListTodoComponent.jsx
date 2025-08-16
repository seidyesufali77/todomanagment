import React from 'react';
import { useState, useEffect } from 'react';
import {deleteTodo, getAll, inCompleteTodo} from "../services/TodoService.jsx";
import Backdrop from "bootstrap/js/src/util/backdrop.js";
import Button from "bootstrap/js/src/button.js";
import {completeTodo} from "../services/TodoService.jsx";
import {useNavigate} from "react-router-dom";
const ListTodoComponent = () => {
// useEffect is a hook that allows you to perform side effects in functional components
    //create useState to manage the state of the todo list
const [todos, setTodos] = useState([]);
const navigate = useNavigate();
    // useState is a hook that allows you to add state to functional components
useEffect (() => {
    listTodos();
}, []);
    function listTodos() {
        getAll().then((response) => {
            setTodos(response.data);

        }).catch((error) => {
            console.error("There was an error fetching the todos!", error);
        })
    }
    function addNewTodo() {
        // This function will be used to add a new todo
        // For now, we will just log a message to the console
        navigate("/addTodo");
       // console.log("Add New Todo button clicked");
        // You can implement the logic to navigate to the Add Todo page or open a modal here
    }
    function  updateTodo(id) {
        console.log(id);
        // This function will be used to update a todo
        // For now, we will just log a message to the console
        navigate(`/update/${id}`)
        //console.log("Update Todo button clicked for id:", id);
        // You can implement the logic to navigate to the Edit Todo page or open a modal here
    }
    function removeTodo(id){
        const  confirmation = window.confirm("Are you sure you want to delete this todo?");
        if (!confirmation) {
            return;
        }
        deleteTodo(id).then((response) => {
            listTodos();

        }).catch(error => {
            console.error("There was an error deleting the todo!", error);
            alert("Error deleting todo: " + error.message);
        })


    }
    function markCompleteTodo(id) {
        completeTodo(id).then((response) => {
            listTodos();
        }).catch(error => {
            console.error("There was an error marking the todo as complete!", error);
            alert("Error marking todo as complete: " + error.message);
        });
    }

    function markInCompleteTodo(id) {
        inCompleteTodo(id).then((response) => {
            listTodos();
        }).catch(error => {
            console.error("There was an error marking the todo as incomplete!", error);
            alert("Error marking todo as incomplete: " + error.message);
        });
    }
    return (
        <div className= "container">
            <h1 className="text-center"> List of todos</h1>
           <button className="btn btn-primary mb-2" onClick={addNewTodo}>Add New Todo</button>
          <div style={{  maxHeight: "400px", overflowY: "auto" }}>
            <table className="table" >
                <thead>
                    <tr>
                        <th scope="col">Title</th>
                        <th scope="col">Description</th>
                        <th scope="col">Completed</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        todos.map(todo => (
                        <tr key={todo.id}>
                            <td>{todo.title}</td>
                            <td>{todo.description || 'No description'}</td>
                            <td>{todo.completed ? 'Yes' : 'No'}</td>
                            <td style={{ display: 'flex', justifyContent: 'space-between' }}>
                                <button className="btn btn-info" onClick={() => updateTodo(todo.id)}>Update</button>
                                <button className="btn btn-danger" onClick={() => removeTodo(todo.id)} style={{marginLeft:"15px"}}>Delete</button>
                                <button className="btn btn-success" onClick={() => markCompleteTodo(todo.id)} style={{marginLeft:"15px"}}>Complete</button>
                                <button className="btn btn-info" onClick={() => markInCompleteTodo(todo.id)} style={{marginLeft:"15px"}}>In Complete</button>

                            </td>
                        </tr>
                    ))}
                </tbody>

            </table>

          </div>
        </div>
    );
}
export  default ListTodoComponent;