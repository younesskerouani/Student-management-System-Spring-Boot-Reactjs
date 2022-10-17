import { Link , useParams } from 'react-router-dom'
import axios from 'axios';
import React, { useEffect, useState } from 'react'



export default function ViewUser() {


    const {id}=useParams();

        const [user,setUser]= useState({
            name:"",
            username:"",
            email:""
        });

        const {name,username,email}= user;

        useEffect(()=> {
            loadUser();
        },[])


        const loadUser = async () => {
            const result = await axios.get(`http://localhost:8080/user/${id}`)
            setUser(result.data)
        }

  return (
    <div className='container'>
      <div className='row'>

        <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
        <h2 className="text-center m-4">User Details</h2>

        <div className='card'>
            <div className='card-header'>
                Details of user id:
                <ul className='list-group list-group-flush'>
                    <li className='list-group-item'>
                        <b>Name:  </b> {name}
                    </li>

                    <li className='list-group-item'>
                        <b>Username:  </b>{username}
                    </li>

                    <li className='list-group-item'>
                        <b>Email:  </b>{email}
                    </li>

                </ul>
            </div>
        </div>

        <Link className='btn btn-primary my-2' to={"/"}>
            Precedent
        </Link>
        </div>
       </div>
    </div>

  )
}
