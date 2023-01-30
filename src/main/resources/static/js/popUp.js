
console.log(document.URL)
function popFunction(){
    if(document.URL==="http://localhost:8081/profile/profile-detail"){
        document.getElementById("addBalanceModal").classList.remove("flex")
        document.getElementById("addBalanceModal").classList.add("hidden")
    }else if(document.URL==="http://localhost:8081/profile/profile-detail-popup"){
        document.getElementById("addBalanceModal").classList.remove("hidden")
        document.getElementById("addBalanceModal").classList.add("flex")
        console.log(document.getElementById("addBalanceModal").classList)
    }
}
popFunction()
