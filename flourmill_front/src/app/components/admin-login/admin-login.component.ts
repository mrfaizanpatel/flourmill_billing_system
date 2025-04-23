import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-admin-login',
  imports: [FormsModule,CommonModule,ReactiveFormsModule],
  templateUrl: './admin-login.component.html',
  styleUrl: './admin-login.component.css'
})
export class AdminLoginComponent implements OnInit{
  name: string = '';
  password: string = '';
  errorMessage: string = '';
  ngOnInit(): void {
    this.signupForm = this.fb.group({
      fullName: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required],
      role: ['User'], // Default role set to "User"
      phoneNumber: ['', Validators.required],
      address: ['', Validators.required]
    });
  }
  onSignupSubmit(): void {
    if (this.signupForm.valid) {
      const signupData = this.signupForm.value;
      this.http.post('http://localhost:8000/api/adminsignup', signupData).subscribe(
        response => {
          console.log('Admin signed up successfully', response);
          alert('Signup successful!');
          this.signupForm.reset({
            role: 'User' // Reset role to default after clearing form
          });
        },
        error => {
          console.error('Signup error:', error);
          alert('Signup failed!');
        }
      );
    } else {
      alert('Please fill all required fields!');
    }
  }
  // constructor(private http: HttpClient, private router: Router) {}
  constructor(private userService: UserService,private router:Router,private fb: FormBuilder,private http: HttpClient) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  // login() {
  //   const admin = {
  //     name: this.loginForm.value.username,
  //     password: this.loginForm.value.password
  //   };   
  //   this.http.post('http://localhost:8000/api/mylogin', admin, { responseType: 'text' }).subscribe(
  //     response => {
  //       console.log(response);
  //       sessionStorage.setItem('isLoggedIn', 'true');
  //       // Navigate to dashboard on success
  //       this.router.navigate(['/dashboard']);
  //     },
  //     error => {
  //       this.errorMessage = 'Invalid credentials';
  //       console.error(error);
  //     }
  //   );
  // }
  onLogin() {
    this.http.post<any>('http://localhost:8000/api/mylogin', this.loginData).subscribe({
      next: (response) => {
        console.log('Login successful:', response);
        sessionStorage.setItem('isLoggedIn', 'true');
        sessionStorage.setItem('userRole', response.role); // 👈 Save the role here

        // Save user info or role in session/localStorage if needed
        this.router.navigate(['/dashboard']);
      },
      error: () => {
        this.errorMessage = 'Invalid username or password';
      }
    });
  }
  
isSignupMode = false;

loginForm!: FormGroup;
signupForm!: FormGroup;
message = '';

makesignupvisible(){
  this.isSignupMode=true;
}
makesignupinvisible(){
  this.isSignupMode=false;
}
loginData = { username: '', password: '' };
theerrorMessage = '';

}
