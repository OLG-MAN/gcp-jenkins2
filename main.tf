### Bucket for startup scripts
resource "google_storage_bucket" "startup-bucket101" {
  name          = "startup-bucket-jenkins"
  force_destroy = "true"
  location      = "US"
}

### Copy startup master script to bucket 
resource "google_storage_bucket_object" "startup-script" {
  name   = "startup.sh"
  source = "./startup.sh"
  bucket = "startup-bucket-jenkins"

  depends_on = [google_storage_bucket.startup-bucket101]

}

### Copy startup node script to bucket 
resource "google_storage_bucket_object" "startup-node" {
  name   = "startupnode.sh"
  source = "./startupnode.sh"
  bucket = "startup-bucket-jenkins"

  depends_on = [google_storage_bucket.startup-bucket101]
}

### Add permission for service account
resource "google_storage_bucket_iam_member" "member" {
  bucket = google_storage_bucket.startup-bucket101.name
  role = "roles/storage.admin"
  member = "serviceAccount:jenkins@gcp-jenkins-327609.iam.gserviceaccount.com"
}

### Jenkins Master
resource "google_compute_instance" "jenkins1" {
  name         = "jenkins-master"
  machine_type = "e2-medium"
  zone         = var.zone

  tags = ["http-server"]

  boot_disk {
    initialize_params {
      image = "debian-cloud/debian-10"
    }
  }

  network_interface {
    network = "default"

    access_config {
      
    }
  }

  metadata = {
    startup-script-url = "gs://startup-bucket-jenkins/startup.sh"
  }

    service_account {
    email  = "jenkins@gcp-jenkins-327609.iam.gserviceaccount.com"
    scopes = ["cloud-platform"]
  }

  depends_on = [
    google_storage_bucket_object.startup-script,
    google_storage_bucket_object.startup-node
  ]
}

### Jenkins Slave
resource "google_compute_instance" "jenkins2" {
  name         = "jenkins-slave"
  machine_type = "e2-medium"
  zone         = var.zone

  tags = ["http-server"]

  boot_disk {
    initialize_params {
      image = "debian-cloud/debian-10"
    }
  }

  network_interface {
    network = "default"

    access_config {
      
    }
  }

  metadata = {
    startup-script-url = "gs://startup-bucket-jenkins/startupnode.sh"
  }

    service_account {
    email  = "jenkins@gcp-jenkins-327609.iam.gserviceaccount.com"
    scopes = ["cloud-platform"]
  }

  depends_on = [
    google_storage_bucket_object.startup-script,
    google_storage_bucket_object.startup-node
  ]
}