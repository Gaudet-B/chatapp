import { useEffect, useState } from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'

import Navigation from './components/Navigation'
import Landing from './views/Landing'
import Chat from './views/Chat'

function App() {

  const [ loggedIn, setLoggedIn ] = useState(false)

  useEffect(() => {
    if (sessionStorage.getItem("loggedIn")) setLoggedIn(true)
  }, [])

  return (
    <div className="bg-secondary App" style={{width: "100vw", minHeight: "100vh"}}>
      <BrowserRouter>

        <Navigation />

        <Routes>

          <Route exact path="/" element={(!loggedIn) ? <Landing setLoggedIn={setLoggedIn} /> : <Chat />} />

        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
