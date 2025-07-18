import { useState} from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Login = () => {
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate()

  async function handleLogin(event){
    event.preventDefault();
    try{
        const token = await axios.post("https://springbootfirst-1-xp06.onrender.com/api/auth/login",{userName,password})
        console.log(token);
        navigate("/dashboard")
    } catch (e){
        console.log("Login Error", e);
        alert("Invalid Cred")
    }
    console.log("Form Submitted");
  }
  return (
    <div>
      <h2>Login</h2>
      <div>
        <form onSubmit={handleLogin}>
          <label htmlFor="userName">User Name</label>
          <input
            id="userName"
            name="userName"
            value={userName}
            type="text"
            onChange={(e) => setUserName(e.target.value)}
          />
          <br /> <br />
          <label htmlFor="password">Password</label>
          <input
            id="password"
            name="password"
            value={password}
            type="password"
            onChange={(e) => setPassword(e.target.value)}
          />
          <br />
          <br />
          <button type="submit">Login</button>
        </form>
      </div>
    </div>
  );
};
export default Login;