import React from 'react'
import ArrowForwardIcon from '@mui/icons-material/ArrowForward';
const Contact = () => {
  return (
    <div style={{backgroundImage:"url('https://img.freepik.com/free-vector/raised-colorful-volunteers-hands-concept-background-with-love-heart-vector_1017-48261.jpg?uid=R121066045&ga=GA1.1.1977216938.1680318996&semt=ais_hybrid')"}}>
      <h2 style={{textAlign:"center",fontWeight:"bold",fontFamily:"cursive"}}>Post volunteering opportunity here</h2>
      <div style={{textAlign:"center",fontFamily:"cursive"}}>
<input type='text' placeholder='Title of your social work' style={{width:"300px", textAlign:"center"}} ></input> <br></br> 
<input type='text' placeholder='Description' style={{width:"300px", textAlign:"center"}} ></input><br></br>      
<br></br><label for="cars" style={{fontWeight:"bolder", color:"gray",textAlign:"center"}}>Choose a category:</label>
<select name="vol" id="vol" style={{ textAlign:"center"}}>
  <option value="Edu">Education</option>
  <option value="health">Healthcare</option>
  <option value="food">Donate Food</option>
  <option value="Clean">Clean India</option>
</select>
<br></br>
<input type='text' placeholder='Enter Location Here' style={{width:"300px", textAlign:"center",textAlign:"center"}} ></input>  

<br></br><label for="cars" style={{fontWeight:"bolder", color:"gray",textAlign:"center"}}>Choose the City:</label>
<select name="vol" id="vol" style={{ textAlign:"center"}}>
  <option value="Edu">Haryana</option>
  <option value="health">Delhi</option>
  <option value="food">Mumbai</option>
  <option value="Clean">Dehradun</option>
</select>

<br></br>
<input type='text' placeholder='What Skills you want?' style={{width:"300px",textAlign:"center"}} ></input>  
<br></br><label for="start">Start-date:</label>
<input type="date" id="start" name="start"></input>

<br></br><label for="end">End-date:</label>
<input type="date" id="end" name="end"></input>
<br></br>
<button style={{background:"black",color:"white",border:"1px solid white",height:"60px",borderRadius:"10%"}}>Submit <ArrowForwardIcon /></button>
</div>

<br></br>
<br></br>
 </div>
  )
}

export default Contact
