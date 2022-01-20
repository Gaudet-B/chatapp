import { useState } from 'react'
import { Link } from 'react-router-dom'

import axios from 'axios'

import Form from '../components/Form'


const Landing = props => {

    const { setLoggedIn, setLoading } = props

    return (

        <div className="d-flex flex-column text-light text-center p-3" style={{ width: "90%", maxWidth: "700px", margin: "auto" }}>
            <h1 className="mt-2 mb-5">Welcome to <strong className="display-3 mx-2"> chata</strong> </h1>
            <Form setLoggedIn={setLoggedIn} setLoading={setLoading} />
            <div className="d-flex flex-row justify-content-end pe-3 mt-5 fw-bold fs-5" >
                <Link to="/chat" className="link-light" >or chat as a guest &gt;</Link>
            </div>
        </div>

    )
}

export default Landing
