import React, {useEffect, useState} from 'react';
import {getTodo, saveTodo, updateTodo} from "../services/TodoService.jsx";
import { useNavigate ,useParams } from "react-router-dom";

const TodoComponent = () => {
    const navigate = useNavigate();
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [completed, setCompleted] = useState(false);
    const { id } = useParams();

    function saveOrUpdateTodo(e) {
        e.preventDefault();
        const todo = { title, description, completed };
        console.log(todo);
        if (id) {
            updateTodo(id, todo).then((response) => {
                navigate("/todos");
            }).catch(error => {
                console.error("There was an error updating the todo!", error);
                alert("Error updating todo: " + error.message);
            })
        }
        else
        {
            saveTodo(todo)
                .then((response) => {
                    console.log(response.data);
                    alert("Todo saved successfully!");
                    navigate("/todos/add");
                    navigate("/todos");
                })
                .catch(error => {
                    console.error("There was an error saving the todo!", error);
                    alert("Error saving todo: " + error.message);
                });
        }

    }






function pageTitle() {
    if (id) {
        return <h2 className='text-center'>Update Todo</h2>
    }
    else{
      return  <h2 className='text-center'>Add Todo</h2>
    }
}

    useEffect(() => {
        if(id){
            getTodo(id).then(
                (response) => {
                    setTitle(response.data.title);
                    setDescription(response.data.description);
                    setCompleted(response.data.completed);
                }
            ).catch(error => {
                console.error("There was an error fetching the todo!", error);
                alert("Error fetching todo: " + error.message);
            })
        }
    },[id])


    return (
        <div className="container">
            <div className='card col-md-6 offset-md-3'>
                {pageTitle()}
                <div className="card-body">
                    <form>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Todo Title:</label>
                            <input
                                type='text'
                                className='form-control'
                                placeholder='Enter Todo Title'
                                value={title}
                                onChange={(e) => setTitle(e.target.value)}
                            />
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Todo Description</label>
                            <input
                                type='text'
                                className='form-control'
                                placeholder='Enter Todo Description'
                                value={description}
                                onChange={(e) => setDescription(e.target.value)}
                            />
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Todo Completed</label>
                            <select
                                className='form-control'
                                value={completed}
                                onChange={(e) => setCompleted(e.target.value === "true")}
                            >
                                <option value="false">No</option>
                                <option value="true">Yes</option>
                            </select>
                        </div>

                        <button className='btn btn-success' onClick={saveOrUpdateTodo}>
                            Submit
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default TodoComponent;