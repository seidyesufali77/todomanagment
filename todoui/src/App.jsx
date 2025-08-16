
import { useState } from 'react'
import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import ListTodoComponent from "./Component/ListTodoComponent.jsx";
import HeaderComponent from "./Component/HeaderComponent.jsx";
import FooterComponent from "./Component/FooterComponent.jsx";
import { BrowserRouter , Routes , Route } from 'react-router-dom';
import TodoComponent from "./Component/TodoComponent.jsx";
//App component is the core component of the application
// It is the main component that renders the UI
// react has only one single root componebt  and  there is single index.html file
// and the root component is rendered in that file div tag with id root
function App() {
  const [count, setCount] = useState(0)

  return (
    <>
        <BrowserRouter>

        <HeaderComponent />
            <Routes>
                //http://localhost:8080/
                <Route path="/" element={<ListTodoComponent />} />
                //http://localhost:8080/api/todos
                <Route path="/todos" element={<ListTodoComponent />} />
                //http://localhost:8080/api/todos/addTodo
                <Route path="/addTodo" element={<TodoComponent />} />
                //http://localhost:8080/api/todos/update/:id
                <Route path="/update/:id" element={<TodoComponent />} />


            </Routes>

        <FooterComponent  />
        </BrowserRouter>
    </>
  )
}

export default App
