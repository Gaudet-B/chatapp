import burger from '../assets/burger-menu-vector.png'

const Navigation = () => {



    return (

        <div className="d-flex flex-row bg-primary mb-4 px-3 justify-content-end" style={{ width: "100%", height: "70px", boxShadow: "0px 5px 6px #0d2149c7" }} >
            <a>
                <img src={burger}/>
            </a>
        </div>
    )

}

export default Navigation
