import { useEffect, useState } from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'

import Navigation from './components/Navigation'
import Landing from './views/Landing'
import Chat from './views/Chat'

function App() {

  const [ loggedIn, setLoggedIn ] = useState(false)
  // const [ loading, setLoading ] = useState(true)

  useEffect(() => {
    if (sessionStorage.getItem("loggedIn")) setLoggedIn(true)

    const script = document.createElement("script")
        script.src = "https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"
        document.body.appendChild(script)

    return(
        document.body.removeChild(script)
    )

  }, [])

  return (
    <div className="bg-secondary pb-3 App" style={{width: "100vw", minHeight: "100vh", maxHeight: "fit-content"}}>
      <BrowserRouter>

        <Navigation loggedIn={loggedIn} setLoggedIn={setLoggedIn} />

        <Routes>

          <Route exact path="/" element={(!loggedIn) ? <Landing setLoggedIn={setLoggedIn} /> : <Chat />} />

        </Routes>

      </BrowserRouter>
    </div>
  );
}

export default App;
