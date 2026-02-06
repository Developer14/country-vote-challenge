import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, inject, OnDestroy, OnInit, output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { debounceTime } from 'rxjs';
import { VoteEventModel } from '../../models/vote.event.model';

@Component({
  selector: 'cv-user-form',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './user-form.html',
  styleUrl: './user-form.scss',
})
export class UserForm implements OnInit, OnDestroy {

  private formBuilder: FormBuilder = inject(FormBuilder);
  private changeDetector = inject(ChangeDetectorRef);

  protected status: string | null = null;
  protected form!: FormGroup;
  
  readonly onSubmit = output<VoteEventModel>();
  
  ngOnInit(): void {
    this.buildForm();
  }

  ngOnDestroy(): void {
    // Cleanup if necessary

  }

  private buildForm() {
    this.form = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', Validators.compose([Validators.required, Validators.email])],
      country: ['', Validators.required]
    });

    this.form.valueChanges.pipe(debounceTime(200)).subscribe((value) => {
      console.log(value);
    });
  }

  protected submitForm() {
    if (this.form.valid) {
      this.onSubmit.emit({request: this.form.value, callback: (status: string) => this.updateView(status)});
    } else {
      this.form.markAllAsTouched();
    }
  }

  protected reset() {
    this.status = null;
    this.changeDetector.detectChanges();
  }

  private updateView(status: string) {
    this.processStatus(status);
    this.changeDetector.detectChanges();
  }

  private processStatus(status: string) {
    if (status === 'success') {
      console.log('Vote submitted successfully.');
      this.form.reset();
      this.status = 'success';
      return;
    }

    console.error('There was an error submitting your vote. Please try again.');
    this.status = status;
  }

}
